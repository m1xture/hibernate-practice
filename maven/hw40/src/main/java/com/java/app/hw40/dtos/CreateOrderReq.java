package com.java.app.hw40.dtos;

import com.java.app.hw40.models.Product;

import java.util.List;

public record CreateOrderReq(List<Product> products) {
}
