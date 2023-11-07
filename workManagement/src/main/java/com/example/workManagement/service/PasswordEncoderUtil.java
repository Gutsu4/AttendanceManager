package com.example.workManagement.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword1 = "user";
        String rawPassword2 = "admin";

        String encodedPassword1 = passwordEncoder.encode(rawPassword1);
        String encodedPassword2 = passwordEncoder.encode(rawPassword2);

        System.out.println("Encoded Password for 'user': " + encodedPassword1);
        System.out.println("Encoded Password for 'admin': " + encodedPassword2);
    }
}
