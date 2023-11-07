package com.example.workManagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String user_id;
    private String password;
    private String name;
    private String role;
}
