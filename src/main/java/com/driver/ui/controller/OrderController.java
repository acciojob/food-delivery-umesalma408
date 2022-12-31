package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto orderDto = orderService.getOrderById(id);
		OrderDetailsResponse responseDto = OrderDetailsResponse.builder()
				.orderId(orderDto.getOrderId())
				.items(orderDto.getItems())
				.cost(orderDto.getCost())
				.userId(orderDto.getUserId())
				.status(orderDto.isStatus())
				.build();
		return responseDto;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {

		OrderDto orderDto = OrderDto.builder()
				.items(order.getItems())
				.cost(order.getCost())
				.userId(order.getUserId())
				.build();
		orderDto = orderService.createOrder(orderDto);
		OrderDetailsResponse responseDto = OrderDetailsResponse.builder()
				.orderId(orderDto.getOrderId())
				.items(orderDto.getItems())
				.cost(orderDto.getCost())
				.userId(orderDto.getUserId())
				.status(orderDto.isStatus())
				.build();
		return responseDto;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{

		OrderDto orderDto = OrderDto.builder()
				.items(order.getItems())
				.cost(order.getCost())
				.userId(order.getUserId())
				.build();
		orderDto = orderService.updateOrderDetails(id , orderDto);
		OrderDetailsResponse responseDto = OrderDetailsResponse.builder()
				.orderId(orderDto.getOrderId())
				.items(orderDto.getItems())
				.cost(orderDto.getCost())
				.userId(orderDto.getUserId())
				.status(orderDto.isStatus())
				.build();
		return responseDto;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {

		orderService.deleteOrder(id);
		OperationStatusModel obj=new OperationStatusModel();
		obj.setOperationResult("Success");
		obj.setOperationName("deleteOrder");
		return obj;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {

		List<OrderDto> orderDtoList = orderService.getOrders();
		List<OrderDetailsResponse> ans = new ArrayList<>();
		int n = orderDtoList.size();
		for(int i=0; i<n; i++)
		{
			OrderDetailsResponse responseDto = OrderDetailsResponse.builder()
					.orderId(orderDtoList.get(i).getOrderId())
					.items(orderDtoList.get(i).getItems())
					.cost(orderDtoList.get(i).getCost())
					.userId(orderDtoList.get(i).getUserId())
					.status(orderDtoList.get(i).isStatus())
					.build();
			ans.add(responseDto);
		}
		return ans;
	}
}
