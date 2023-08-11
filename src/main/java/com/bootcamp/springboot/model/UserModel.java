package com.bootcamp.springboot.model;

import com.bootcamp.springboot.entity.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    private Long id;
    private String name;
    private int age;

    public UserDto generateToDto() {
        return UserDto.builder()
                .name(this.getName())
                .age(this.getAge())
                .build();
    }
}
