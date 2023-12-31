package com.bootcamp.springboot.entity;

import com.bootcamp.springboot.model.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    @Size(min = 3, max = 100)
    private String name;

    @Column(nullable = false)
    @Size(max = 90)
    private Integer age;

    public UserModel generateToModel() {
        return UserModel.builder()
                .id(this.getId())
                .name(this.getName())
                .age(this.getAge())
                .build();
    }
}
