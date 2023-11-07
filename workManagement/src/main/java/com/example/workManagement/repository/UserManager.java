package com.example.workManagement.repository;

import com.example.workManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean login(String user_id,String password){

        String sql = "SELECT password FROM users WHERE user_id = ?";
        System.out.println("Custom login handler invoked.");


        try{
            String storedPassword = jdbcTemplate.queryForObject(sql,String.class,user_id);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.matches(password,storedPassword);

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRoleById(String user_id){

        String sql = "Select role from users where user_id = ?";
        System.out.println("Get User Role");

        try{
            String role = jdbcTemplate.queryForObject(sql,new Object[]{user_id},String.class);
            return role;
        }catch(Exception e) {
            return null;
        }
    }


    public List<User> getAllUser() {
        String sql = "SELECT * FROM users";
        List<Map<String,Object>> user_map = jdbcTemplate.queryForList(sql);
        List<User> users = new ArrayList<>();
        for(Map<String,Object> row : user_map){
            User user = mapToUser(row);
            users.add(user);
        }
        return users;
    }

    public User getUserById(String user_id){
        String sql = "select * from users where user_id = ?;";
        Map<String,Object> user_map = jdbcTemplate.queryForMap(sql,user_id);
        User user = mapToUser(user_map);
        return user;
    }

    public void addUser(){
        //TODO
        //userを追加
    }

    public void editUser(){
        //TODO
        //userの編集
    }



    private User mapToUser(Map<String, Object> row) {
        User user = new User();
        user.setId((Integer)row.get("id"));
        user.setUser_id((String)row.get("user_id"));
        user.setPassword((String)row.get("password"));
        user.setName((String)row.get("user_name"));
        user.setRole((String)row.get("role"));
        return user;
    }

}
