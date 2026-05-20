package com.java.app.hw40.service;

import com.java.app.hw40.models.Order;
import com.java.app.hw40.models.Product;

import java.util.List;

interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Long createOrder(List<Product> products);

    Long updateOrder(Long id, List<Product> products);

    Order deleteOrderById(Long id);
}

