package com.book.userservice.dto;

import com.book.userservice.vo.ResponseOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private LocalDateTime createdAt;

    private List<ResponseOrder> orders;

    private String encryptedPwd;
}
