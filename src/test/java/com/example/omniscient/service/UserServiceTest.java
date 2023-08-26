package com.example.omniscient.service;

import com.example.omniscient.entity.FavoriteEntity;
import com.example.omniscient.entity.UserEntity;
import com.example.omniscient.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Test
    void login() {
        UserEntity u=UserEntity.builder().account("123456789").password("123456789").build();
        UserEntity u1=UserEntity.builder().account("123456789").password("987654321").build();
        System.out.println(userService.login(u));
        System.out.println(userService.login(u1));
    }

    @Test
    void register() {
        UserEntity u= UserEntity.builder().account("123456789").password("123456789").username("user1").build();
        userService.register(u);
        System.out.println(u);
    }

    @Test
    void getInfo() {
        System.out.println(userService.getInfo("123456789"));
    }

    @Test
    void setInfo() {
        UserEntity u= UserEntity.builder().account("123456789").password("987654321").username("user0").build();
        System.out.println(userService.setInfo(u));
    }

    @Test
    void delUser() {
        userService.delUser("123456789");
    }
}