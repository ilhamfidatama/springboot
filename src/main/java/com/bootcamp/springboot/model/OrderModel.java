package com.bootcamp.springboot.model;

import com.bootcamp.springboot.entity.OrderEntity;
import com.bootcamp.springboot.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    private Long id;
    private String invoiceNumber;
    private Long userId;

    public OrderEntity toEntity(UserEntity user) {
        return OrderEntity.builder()
                .User(user)
                .invoiceNumber(this.invoiceNumber)
                .build();
    }
}
