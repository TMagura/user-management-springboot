package com.teebag.users.controller;


import com.teebag.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//use crossorigin to communicate with the front end on diff ports
@CrossOrigin(value ="http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        return  userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return  userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user=null;
        user=userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("id") Long id){
        boolean delete = false;
        delete = userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", delete);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
        user = userService.updateUser(id,user);
        return ResponseEntity.ok(user);
    }
}
