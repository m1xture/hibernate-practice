package com.java.app.hw40.dtos;

import com.java.app.hw40.models.Order;

import java.util.List;

public record OrdersListRes(List<Order> orders) {
}
