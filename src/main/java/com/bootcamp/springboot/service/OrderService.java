package com.bootcamp.springboot.service;

import com.bootcamp.springboot.entity.OrderEntity;
import com.bootcamp.springboot.entity.UserEntity;
import com.bootcamp.springboot.model.OrderModel;
import com.bootcamp.springboot.model.Response;
import com.bootcamp.springboot.repository.OrderRepository;
import com.bootcamp.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> createOrder(Long userId, OrderModel order) {
        Optional<UserEntity> userDb = userRepository.findById(userId);
        if (userDb.isPresent()) {
            UserEntity user = userDb.get();
            OrderEntity newOrder = order.toEntity(user);

            var savedOrder = orderRepository.save(newOrder);
            ResponseEntity.accepted().body(
                    Response.builder()
                            .data(savedOrder)
                            .message("Success.")
                            .build()
            );
        }

        return ResponseEntity.badRequest().body(
                Response.builder()
                        .err(true)
                        .message("User Id not found!")
                        .build()
        );
    }

}
