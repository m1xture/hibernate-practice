package com.java.app.hw37.service;

import com.java.app.hw37.OrderNotFoundException;
import com.java.app.hw37.dto.OrderDto;
import com.java.app.hw37.dto.OrderReq;
import com.java.app.hw37.entity.Order;
import com.java.app.hw37.entity.Product;
import com.java.app.hw37.mapper.OrderMapper;
import com.java.app.hw37.repo.OrderRepo;
import com.java.app.hw37.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;

    private final OrderRepo orderRepository;
    private final ProductRepo productRepo;

    public Long createOrder(List<UUID> productsIds) {

        List<Product> products = productRepo.findAllById(productsIds);

        Order order = new Order();
        order.setProducts(products);
        order.setOrderDate(new Date());
        order.setCost(products.stream().mapToDouble(Product::getCost).sum());
        Long id = orderRepository.save(order).getId();
        log.info("Order created with id: {}", id);
        return id;
    }


    public Order getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        log.info("Order found with id: {}", orderId);
        return order;
    }

    public Long updateOrder(Long id, OrderReq orderReq) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        List<Product> products = productRepo.findAllById(orderReq.productsIds());
        order.setProducts(products);
        orderRepository.save(order);
        log.info("Order updated with id: {}", id);
        return order.getId();
    }

    @Transactional
    public Order deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        Hibernate.initialize(order.getProducts());
        orderRepository.deleteById(orderId);
        log.info("Order deleted with id: {}", orderId);
        return order;
    }

}
