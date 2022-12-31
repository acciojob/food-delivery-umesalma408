package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order){

        OrderEntity orderEntity = OrderEntity.builder()
                .orderId(order.getOrderId())
                .cost(order.getCost())
                .items(order.getItems())
                .userId(order.getUserId())
                .status(order.isStatus())
                .build();
        orderRepository.save(orderEntity);
        return order;
    }
    @Override
    public OrderDto getOrderById(String orderId) throws Exception{
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = OrderDto.builder()
                .id(orderEntity.getId())
                .orderId(orderEntity.getOrderId())
                .cost(orderEntity.getCost())
                .items(orderEntity.getItems())
                .userId(orderEntity.getUserId())
                .status(orderEntity.isStatus())
                .build();
        return orderDto;
    }
    @Override
   public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception{
        OrderEntity oldOrder = orderRepository.findByOrderId(orderId);
        OrderEntity newOrder = OrderEntity.builder()
                .id(oldOrder.getId())
                .orderId(order.getOrderId())
                .cost(order.getCost())
                .items(order.getItems())
                .userId(order.getUserId())
                .status(order.isStatus())
                .build();
        orderRepository.save(newOrder);
        return order;
    }
    @Override
    public void deleteOrder(String orderId) throws Exception{
        orderRepository.deleteById(Long.valueOf(orderId));
    }
    @Override
    public List<OrderDto> getOrders(){

        List<OrderEntity> orderEntityList = (List<OrderEntity>)orderRepository.findAll();
        List<OrderDto> ans = new ArrayList<>();
        int n = orderEntityList.size();
        for(int i = 0; i<n; i++)
        {
            OrderDto orderDto = OrderDto.builder()
                    .id(orderEntityList.get(i).getId())
                    .orderId(orderEntityList.get(i).getOrderId())
                    .cost(orderEntityList.get(i).getCost())
                    .items(orderEntityList.get(i).getItems())
                    .userId(orderEntityList.get(i).getUserId())
                    .status(orderEntityList.get(i).isStatus())
                    .build();
            ans.add(orderDto);
        }
        return ans;
    }
    }
