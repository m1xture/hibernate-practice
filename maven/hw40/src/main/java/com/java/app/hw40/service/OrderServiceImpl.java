package com.java.app.hw40.service;


import com.java.app.hw40.models.Order;
import com.java.app.hw40.models.Product;
import com.java.app.hw40.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private double calculateProductsTotalCost(List<Product> products) {
        return products.stream().mapToLong(Product::getId).max().orElse(0L);
    }

    public Long createOrder(List<Product> products) {
        return orderRepository.create(products, calculateProductsTotalCost(products));
    }

    public List<Order> getAllOrders() {
        return orderRepository.getOrders();
    }

    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id).orElseThrow(() -> new IllegalArgumentException("Order Not Found"));
    }

    public Long updateOrder(Long id, List<Product> products) {
        return orderRepository.updateOrder(id, products, calculateProductsTotalCost(products));
    }

    public Order deleteOrderById(Long id) {
        return orderRepository.deleteOrder(id);
    }
}