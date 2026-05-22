package com.java.app.hw37.controller;

import com.java.app.hw37.dto.OrderDto;
import com.java.app.hw37.dto.OrderReq;
import com.java.app.hw37.entity.Order;
import com.java.app.hw37.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public Long createOrder(@Valid @RequestBody OrderReq orderReq) {
        System.out.println(orderReq.productsIds());
        return orderService.createOrder(orderReq.productsIds());
    }

    @GetMapping("{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PutMapping("{orderId}")
    public Long updateOrder(@PathVariable Long orderId, @Valid @RequestBody OrderReq orderReq) {
        return orderService.updateOrder(orderId, orderReq);
    }

    @DeleteMapping("{orderId}")
    public Order deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }

}
