package com.bootcamp.springboot.controller;

import com.bootcamp.springboot.DatabaseListener;
import com.bootcamp.springboot.model.Response;
import com.bootcamp.springboot.model.UserModel;
import com.bootcamp.springboot.service.UserService;
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
        var result = services.getUser();
        return ResponseEntity.ok().body(
            Response.builder()
                .data(result)
                .message("Success")
                .build()
        );
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserModel userModel) {
        Long result = services.addUser(userModel);
        return ResponseEntity.ok().body(
            Response.builder().data(result).message("success message").build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> filterById(@PathVariable Long id){
        var user = services.getUserById(id);
        return ResponseEntity.ok().body(
                Response.builder()
                        .data(user)
                        .message("success")
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody UserModel newUser) {
        try {
            var result = services.updateUser(id, newUser);
            return ResponseEntity.ok().body(
                    Response.builder()
                            .message("update user success")
                            .data(result)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .err(true)
                            .message(e.getMessage())
                            .build()
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        Boolean isDeleted = services.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok().body(
                    Response.builder()
                            .message("deleted user success")
                            .build()
            );
        }
        return ResponseEntity.badRequest().body(
                Response.builder()
                        .message("deleted user failed")
                        .err(true)
                        .build()
        );
    }

}
