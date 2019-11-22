package com.fosung.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

    public static void main(String[] args){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String finalPassword = bCryptPasswordEncoder.encode("123456");

        System.out.println(finalPassword);
    }
}
