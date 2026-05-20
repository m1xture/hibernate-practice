package com.java.app.hw40.controller;

import com.java.app.hw40.dtos.CreateOrderReq;
import com.java.app.hw40.dtos.OrderIdRes;
import com.java.app.hw40.dtos.OrderRes;
import com.java.app.hw40.dtos.OrdersListRes;
import com.java.app.hw40.service.OrderServiceImpl;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("orders")
public class OrderController {
    final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public OrdersListRes getAllOrders() {
        return new OrdersListRes(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public OrderRes getOrderById(@PathVariable Long id) {
        return new OrderRes(orderService.getOrderById(id));
    }

    @PostMapping
    public OrderIdRes createOrder(@RequestBody CreateOrderReq createOrderReq) {
        return new OrderIdRes(orderService.createOrder(createOrderReq.products()));
    }

    @PatchMapping("/{id}")
    public OrderIdRes updateOrder(@PathVariable Long id, @RequestBody CreateOrderReq createOrderReq) {
        return new OrderIdRes(orderService.updateOrder(id, createOrderReq.products()));
    }

    @DeleteMapping("/{id}")
    public OrderRes deleteOrderById(@PathVariable Long id) {
        return new OrderRes(orderService.deleteOrderById(id));
    }
}
