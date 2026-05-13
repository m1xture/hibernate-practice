package com.java.app.hw37.dto;

import com.java.app.hw37.entity.Product;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private List<Product> products;
    private double cost;
}