package com.bootcamp.springboot.model;

import com.bootcamp.springboot.entity.UserEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    @Null
    private Long id;

    @Size(min = 4, message = "field[name] should have minimum 4 characters")
    private String name;

    @Min(value = 18, message = "Only for adult")
    private int age;

    public UserEntity generateToDto() {
        return UserEntity.builder()
                .name(this.getName())
                .age(this.getAge())
                .build();
    }
}
