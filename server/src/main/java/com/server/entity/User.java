package com.server.entity;


@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public User(String name,String email,Long id){
        this.id = id;
        this.name = name;
        this.email= email;
    }

    




}