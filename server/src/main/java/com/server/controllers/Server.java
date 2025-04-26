package com.server.controllers;

import com.server.entity.User;
import com.server.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.net.http.HttpRequest;
// import org.springframework.*;
import java.util.*;


@RestController
@RequestMapping("/")
public class Server{

    private UserRepository userRepository;

    @GetMapping
    public String test(HttpServletRequest req){

        String method = req.getMethod();
        StringBuffer url = req.getRequestURL();
        String ip = req.getRemoteAddr();
        String host = req.getRemoteHost();
        int port = req.getRemotePort();
        



        // return "Application working ";
        return String.format(
            "Method: %s, URL: %s, IP: %s, Host: %s, Port: %s",
            method,url,ip,host,port
        );
    }

    @GetMapping("/test/{name}")
    public ResponseEntity<String> ping(@PathVariable String name){
        // return ResponseEntity.ok("Success");
        String res =  name+ "pong";
        return ResponseEntity.ok(res);


    }


}
