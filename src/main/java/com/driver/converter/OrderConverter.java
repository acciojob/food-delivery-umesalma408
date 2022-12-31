package com.driver.converter;

import com.driver.io.entity.OrderEntity;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.shared.dto.OrderDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderConverter {

    public static OrderEntity convertDtoToEntity(OrderDto orderDto) {
       return OrderEntity.builder().orderId(orderDto.getOrderId()).cost(orderDto.getCost()).items(orderDto.getItems()).build();
    }
    public static OrderDetailsResponse convertDtoToEntity(OrderEntity orderEntity){
        return OrderDetailsResponse.builder().orderId(orderEntity.getOrderId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).build();

    }
}

