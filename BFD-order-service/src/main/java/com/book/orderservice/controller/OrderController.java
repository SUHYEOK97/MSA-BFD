package com.book.orderservice.controller;

import com.book.orderservice.dto.OrderDto;
import com.book.orderservice.entity.OrderEntity;
import com.book.orderservice.messageQueue.KafkaProducer;
import com.book.orderservice.service.OrderService;
import com.book.orderservice.vo.RequestOrder;
import com.book.orderservice.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
public class OrderController {

    OrderService orderService;
    KafkaProducer kafkaProducer;

    @Autowired
    public OrderController(OrderService orderService, KafkaProducer kafkaProducer) {
        this.orderService = orderService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/healthCheck")
    public String status(){
        return "it's working on";
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable String userId,
                                                     @RequestBody RequestOrder orderDetails){
        /* JPA */
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);

        ResponseOrder returnValue = mapper.map(createdOrder, ResponseOrder.class);

        /* Kafka */
        kafkaProducer.send("example-catalog-topic", orderDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getAllOrders(@PathVariable String userId){

        Iterable<OrderEntity> allOrdersByUserId = orderService.getAllOrdersByUserId(userId);

        List<ResponseOrder> list = new ArrayList<>();

        allOrdersByUserId.forEach(v->{
            list.add(new ModelMapper().map(v, ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
