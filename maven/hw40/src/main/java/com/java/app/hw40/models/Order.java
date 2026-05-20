package com.java.app.hw40.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Date creationDate;
    private double totalCost;
    private List<Product> products;
}
