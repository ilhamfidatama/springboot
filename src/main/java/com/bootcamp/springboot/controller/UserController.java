package com.bootcamp.springboot.controller;

import com.bootcamp.springboot.model.Response;
import com.bootcamp.springboot.model.UserModel;
import com.bootcamp.springboot.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService services;

    @GetMapping
    public ResponseEntity<Object> getUsers(){
        var response = services.getUser();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<String>> createUser(@Valid @RequestBody UserModel userModel) {
        return services.addUser(userModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<UserModel>> filterById(@PathVariable Long id){
        ResponseEntity<Response<UserModel>> response = services.getUserById(id);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<UserModel>> updateUser(@PathVariable Long id, @RequestBody UserModel newUser) {
        return services.updateUser(id, newUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteUser(@PathVariable Long id) {
        return services.deleteUser(id);
    }

}
