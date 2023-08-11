package com.bootcamp.springboot.controller;

import com.bootcamp.springboot.model.OrderModel;
import com.bootcamp.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("user/{id}")
    public ResponseEntity<Object> createOrder(@PathVariable Long userId, @RequestBody OrderModel orderModel) {
        return orderService.createOrder(userId, orderModel);
    }

}
