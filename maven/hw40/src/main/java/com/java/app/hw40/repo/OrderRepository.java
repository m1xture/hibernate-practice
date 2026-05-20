package com.java.app.hw40.repo;

import com.java.app.hw40.models.Order;
import com.java.app.hw40.models.Product;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {

    private final AtomicLong idCounter = new AtomicLong(0L);
    @Getter
    private final List<Order> orders = new ArrayList<>();


    public Long create(List<Product> products, double totalCost) {
        var order = new Order();
        order.setProducts(products);
        order.setTotalCost(totalCost);
        order.setCreationDate(new Date());
        order.setId(idCounter.incrementAndGet());
        orders.add(order);
        return order.getId();
    }

    public Optional<Order> getOrderById(Long id) {
        return orders.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Long updateOrder(Long id, List<Product> newProducts, double totalCost) {
        Order order = getOrderById(id).orElseThrow(() -> new IllegalArgumentException("No order with this id was found"));
        order.setProducts(newProducts);
        order.setTotalCost(totalCost);
        return order.getId();
    }

    public Order deleteOrder(Long id) {
        Order foundOrder = getOrderById(id).orElseThrow(() -> new IllegalArgumentException("No order with this id was found"));
        orders.remove(foundOrder);
        return foundOrder;
    }

}
