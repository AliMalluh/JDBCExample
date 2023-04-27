package com.ali.springboot.springjdbctemplate.dao;

import com.ali.springboot.springjdbctemplate.entity.User;

import java.util.List;

public interface UserRepo {
    User saveUser(User user);
    User updateUser(User user);
    User getById(int id);
    List<User> getAllUsers();
    String deleteById(int id);
}
