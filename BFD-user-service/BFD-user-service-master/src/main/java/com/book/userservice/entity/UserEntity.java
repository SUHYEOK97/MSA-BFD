package com.book.userservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;
    private String name;
    private String userId;
    private String encryptedPwd;

}
