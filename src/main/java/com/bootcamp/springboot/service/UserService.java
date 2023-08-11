package com.bootcamp.springboot.service;

import com.bootcamp.springboot.entity.UserEntity;
import com.bootcamp.springboot.model.Response;
import com.bootcamp.springboot.model.UserModel;
import com.bootcamp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Response<List<UserModel>> getUser(){
        var usersDb = userRepository.findAll();
        Response<List<UserModel>> response = new Response<>();

        if (usersDb.isEmpty()) {
            response.setData(new ArrayList<>());
            response.setMessage("Success. Users is empty");
        } else {
            List<UserModel> users = new ArrayList<>();
            usersDb.forEach(userEntity -> users.add(userEntity.generateToModel()));
            response.setData(users);
            response.setMessage("Success.");
        }
        return response;
    }

    public ResponseEntity<Response<UserModel>> getUserById(Long id){
        Optional<UserEntity> userDb = userRepository.findById(id);
        Response<UserModel> response = new Response<>();

        if (userDb.isEmpty()) {
            response.setMessage("User not found.");
            response.setData(null);
            return ResponseEntity
                .status(HttpStatusCode.valueOf(404))
                .body(response);
        }

        UserModel user = new UserModel();
        userDb.ifPresent(userEntity -> {
            user.setId(userEntity.getId());
            user.setName(userEntity.getName());
            user.setAge(userEntity.getAge());
        });
        response.setData(user);
        response.setMessage("Success.");

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Response<String>> addUser(UserModel userModel) {
        UserEntity newUserEntity = userModel.generateToDto();
        Response<String> response = new Response<>();

        try {
            userRepository.save(newUserEntity);
            response.setData("User has been created.");
            response.setMessage("Success created user.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setData("");
            response.setErr(true);
            response.setMessage("Failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<Response<UserModel>> updateUser(Long idUser, UserModel newUser) {
        Response<UserModel> response = new Response<>();

        try {
            Optional<UserEntity> existedUser = userRepository.findById(idUser);

            if (existedUser.isEmpty()) {
                response.setMessage("User doesn't exist!");
                response.setErr(true);
                return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(response);
            }

            UserEntity user = existedUser.get();
            user.setName(newUser.getName());
            user.setAge(newUser.getAge());

            var saved = userRepository.saveAndFlush(user);
            response.setData(saved.generateToModel());
            response.setMessage("success.");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setErr(true);

            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<Response<String>> deleteUser(Long idUser) {
        Optional<UserEntity> checkUsers = userRepository.findById(idUser);
        Response<String> response = new Response<>();
        if (checkUsers.isEmpty()) {
            response.setData("");
            response.setMessage("User doesn't exist!");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(response);
        }

        userRepository.deleteById(idUser);
        Optional<UserEntity> existedUser = userRepository.findById(idUser);
        if (existedUser.isEmpty()){
            response.setData("Success deleted user.");
            response.setMessage("Success.");
            return ResponseEntity.ok(response);
        } else {
            response.setData("Fail deleted user.");
            response.setMessage("failure.");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
