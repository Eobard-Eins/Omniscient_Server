package com.example.omniscient.controller;

import com.example.omniscient.entity.FavoriteEntity;
import com.example.omniscient.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/Favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/CreateFold")
    public ResponseEntity<FavoriteEntity> createFold(@RequestParam("account") String account, @RequestParam("foldName") String foldName){
        return ResponseEntity.ok(favoriteService.createFold(account,foldName));
    }

    @PutMapping("/OperateWord")
    public ResponseEntity<Boolean> operateWord(@RequestParam("account") String account,
                                               @RequestParam("foldName") String foldName,
                                               @RequestParam("do") String op,
                                               @RequestParam("word") String word){
        if(Objects.equals(op, "add"))
            return ResponseEntity.ok(favoriteService.addWord(account+foldName,word));
        if(Objects.equals(op, "del"))
            return ResponseEntity.ok(favoriteService.delWord(account+foldName,word));
        else return ResponseEntity.ok(false);
    }

    @GetMapping("/AllWord")
    public ResponseEntity<List<String>> getAllWord(@RequestParam("account") String account,
                                                   @RequestParam("foldName") String foldName){
        return ResponseEntity.ok(favoriteService.getAllWord(account+foldName));
    }

    @GetMapping("/AllFold")
    public ResponseEntity<List<String>> getAllFold(@RequestParam("account") String account){
        return ResponseEntity.ok(favoriteService.getAllFold(account));
    }

    @DeleteMapping("/Del")
    public ResponseEntity<HttpStatus> delFold(@RequestParam("account") String account,
                                              @RequestParam("foldName") String foldName){
        favoriteService.delFold(account+foldName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
