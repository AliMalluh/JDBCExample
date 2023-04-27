package com.ali.springboot.springjdbctemplate.controller;

import com.ali.springboot.springjdbctemplate.dao.UserRepo;
import com.ali.springboot.springjdbctemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userRepo.saveUser(user);
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userRepo.updateUser(user);
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userRepo.getById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepo.getAllUsers();
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        return userRepo.deleteById(id);
    }
}
