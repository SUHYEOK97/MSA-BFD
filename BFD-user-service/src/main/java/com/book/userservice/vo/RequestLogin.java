package com.book.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull(message = "이메일을 입력해주세요.")
    @Email
    private String email;

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "비밀번호는 8글자 이상입니다.")
    private String pwd;
}
