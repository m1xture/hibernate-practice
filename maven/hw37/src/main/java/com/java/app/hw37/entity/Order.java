package com.java.app.hw37.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@ToString(exclude = "products")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date orderDate;

    private double cost;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)

    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
