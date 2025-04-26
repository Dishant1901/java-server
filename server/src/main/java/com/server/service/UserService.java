package com.server.service;

import com.server.entity.User;
import org.springframework.stereotype.Service;
import com.server.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserService{

    @Autowired
    public  UserRepository userRepository;

    public User createUser (User user){
        try {
            
            userRepository.save(user);
            return user;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User updateUser(User user,Long id){
        User exists = userRepository.findById(id).get();

        if(exists == null){
            return null;
        }

        userRepository.save(user);
        return user;
    }

    public User FindByName(String name){
        return userRepository.findByNameIgnoreCase(name);
    }
}  