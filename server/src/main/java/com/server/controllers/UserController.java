package com.server.controllers;

import com.server.entity.User;
import com.server.service.UserService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/user")

public class UserController{

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/{name}")
        public ResponseEntity<?> findByName(@PathVariable String name){
            User user = userService.FindByName(name);
            if(user == null ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with this name not found");
            }

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        @PutMapping("/update")
        public ResponseEntity<?> updateUser(@RequestParam Long id,@RequestBody User user){
            userService.updateUser(user,id);

            return ResponseEntity.status(HttpStatus.OK).body(user);

        }
    }
