package com.bootcamp.springboot.service;

import com.bootcamp.springboot.entity.UserDto;
import com.bootcamp.springboot.model.UserModel;
import com.bootcamp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserDto> getUser(){
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList = userRepository.findAll();
        System.out.println(userDtoList);
        return userDtoList;
    }

    public UserModel getUserById(Long id){
        Optional<UserDto> userDb = userRepository.findById(id);
        UserModel user = new UserModel();
        userDb.ifPresent(userDto -> {
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setAge(userDto.getAge());
        });
        return user;
    }

    public Long addUser(UserModel userModel) {
        UserDto newUserDto = new UserDto();
        newUserDto.setAge(userModel.getAge());
        newUserDto.setName(userModel.getName());
        UserDto saved = userRepository.save(newUserDto);
        return saved.getId();
    }

    public Object updateUser(Long idUser) throws Exception {
        Optional<UserDto> filterUser = userRepository.findById(idUser);
        if (filterUser.isEmpty()) {
            throw new Exception("user not exist");
        }

    }

}