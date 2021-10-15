package com.book.orderservice.service;

import com.book.orderservice.dto.OrderDto;
import com.book.orderservice.entity.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getAllOrdersByUserId(String userId);
}
