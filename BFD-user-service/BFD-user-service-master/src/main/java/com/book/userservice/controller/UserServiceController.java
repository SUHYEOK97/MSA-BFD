package com.book.userservice.controller;

import com.book.userservice.dto.UserDto;
import com.book.userservice.entity.UserEntity;
import com.book.userservice.service.UserService;
import com.book.userservice.vo.RequestUser;
import com.book.userservice.vo.ResponseUser;
import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class UserServiceController {

    @Autowired
    UserService userService;

    @GetMapping("/healthCheck")
    public String status(){
        return "It's working on";
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody RequestUser requestUser){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);
        return new ResponseEntity<>("Created User", HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getAllUsers(){
        Iterable<UserEntity> userList = userService.getUserByAll();
        List<ResponseUser> res = new ArrayList<>();
        userList.forEach(v -> {
            res.add(new ModelMapper().map(v, ResponseUser.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String userId){
        UserDto userDto = userService.getUserByUserId(userId);
        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }


}
