package com.java.app.hw37.mapper;

import com.java.app.hw37.dto.OrderDto;
import com.java.app.hw37.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto orderReq);

    OrderDto toDto(Order order);
}
