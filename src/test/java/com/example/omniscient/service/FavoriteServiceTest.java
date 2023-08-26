package com.example.omniscient.service;

import com.example.omniscient.repository.FavoriteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavoriteServiceTest {

    @Autowired
    FavoriteService favoriteService;
    @Autowired
    FavoriteRepository favoriteRepository;
    @Test
    void createFold() {
        System.out.println(favoriteService.createFold("523456789","fa1"));
    }

    @Test
    void addWord() {
        favoriteService.addWord("123456789Favorite1","test1");
        favoriteService.addWord("123456789Favorite1","test2");
        favoriteService.addWord("123456789Favorite1","test3");
    }

    @Test
    void delWord() {
        favoriteService.delWord("123456789Favorite1","test3");
    }

    @Test
    void getAllWord() {
        System.out.println(favoriteService.getAllWord("123456789Favorite1"));;
    }

    @Test
    void getAllFold() {
        System.out.println(favoriteService.getAllFold("123456789"));
    }

    @Test
    void delFold() {
        favoriteService.delFold("123456789Favorite1");
    }
}