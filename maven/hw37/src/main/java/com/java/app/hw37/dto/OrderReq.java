package com.java.app.hw37.dto;


import java.util.List;
import java.util.UUID;

public record OrderReq(List<UUID> productsIds) {
}
