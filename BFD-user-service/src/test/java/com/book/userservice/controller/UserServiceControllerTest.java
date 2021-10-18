package com.book.userservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceControllerTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void createUser() {

    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserInfo() {
    }
}