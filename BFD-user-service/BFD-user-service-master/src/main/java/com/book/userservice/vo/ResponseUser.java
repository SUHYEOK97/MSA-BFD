package com.book.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    @Email
    private String email;
    private String name;
    private String userId;
    private List<ResponseOrder> orders;
}
