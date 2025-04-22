package com.server.controllers;

import com.server.entity.User;
import com.server.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

// import org.springframework.*;
import java.util.*;


@RestController
@RequestMapping("/")
public class Server{

    private UserRepository userRepository;

    @GetMapping
    public String test(){
        return "Application working  ";
    }

    @GetMapping("/test/{name}")
    public ResponseEntity<String> ping(@PathVariable String name){
        // return ResponseEntity.ok("Success");
        String res =  name+ "pong";
        return ResponseEntity.ok(res);


    }


}
