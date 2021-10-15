package com.book.userservice.vo;

import lombok.Data;

@Data
public class RequestUser {
    private String email;
    private String pwd;
    private String name;
}
