package com.example.omniscient.controller;

import com.example.omniscient.entity.UserEntity;
import com.example.omniscient.service.UserService;
import com.example.omniscient.unit.UserRegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/Login")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.login(userEntity));
    }

    @PostMapping("/Registered")
    public ResponseEntity<UserRegisterInfo<?>> register(@RequestBody UserEntity userEntity){
        UserRegisterInfo<?> res=userService.register(userEntity);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/getInfo")
    public ResponseEntity<UserEntity> getInfo(@RequestParam("account") String account){
        return ResponseEntity.ok(userService.getInfo(account));
    }

    @PutMapping("/setInfo")
    public ResponseEntity<Boolean> setInfo(@RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.setInfo(userEntity));
    }

    @DeleteMapping("/Del")
    public ResponseEntity<HttpStatus> delUser(@RequestParam("account") String account){
        userService.delUser(account);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
