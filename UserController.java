package com.example.sample.controller;

import com.example.sample.entity.DataEntry;
import com.example.sample.entity.User;
import com.example.sample.repository.repo;
import com.example.sample.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser() {
        return userService.getAll();
    }

    @PostMapping
    public  void create(@RequestBody User user){
        userService.save(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User userInDb = userService.findByUserName(user.getUserName());
        if (userInDb !=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setUserName(user.getPassword());
            userService.save(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
