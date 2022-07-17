package com.example.User.controller;

import com.example.User.entities.User;
import com.example.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "user_app")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity createUser(@RequestBody User user)
    {
        User savedUser= userService.createUser(user);
        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }

    @GetMapping(value="/users")
    public ResponseEntity getAllUsers()
    {
        List<User> userList=userService.getAllUsers();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity getUser(@PathVariable(name = "id")  int id){
        User user = userService.getUserById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PutMapping(value="/users" , consumes = MediaType.APPLICATION_JSON_VALUE ,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser( @RequestBody User user){

        User savedUpdatedUser = userService.updateUser(user);

        return new ResponseEntity(savedUpdatedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value="/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable(name="id") int id)
    {
        userService.deleteUserById(id);
         return new ResponseEntity(null,HttpStatus.OK);
    }



}
