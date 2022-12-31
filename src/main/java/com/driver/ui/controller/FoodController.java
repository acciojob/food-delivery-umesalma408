package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.FoodService;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {
@Autowired
	FoodServiceImpl foodService;
	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDto foodDto = foodService.getFoodById(id);
		FoodDetailsResponse responseDto = FoodDetailsResponse.builder()
				.foodId(foodDto.getFoodId())
				.foodName(foodDto.getFoodName())
				.foodPrice(foodDto.getFoodPrice())
				.foodCategory(foodDto.getFoodCategory())
				.build();

		return responseDto;

	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {

		FoodDto foodDto = FoodDto.builder()
				.foodName(foodDetails.getFoodName())
				.foodPrice(foodDetails.getFoodPrice())
				.foodCategory(foodDetails.getFoodCategory())
				.build();
		foodDto =foodService.createFood(foodDto);
		FoodDetailsResponse responseDto = FoodDetailsResponse.builder()
				.foodId(foodDto.getFoodId())
				.foodName(foodDto.getFoodName())
				.foodPrice(foodDto.getFoodPrice())
				.foodCategory(foodDto.getFoodCategory())
				.build();
		return responseDto;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDto = FoodDto.builder()
				.foodId(id)
				.foodPrice(foodDetails.getFoodPrice())
				.foodCategory(foodDetails.getFoodCategory())
				.foodName(foodDetails.getFoodName())
				.build();
		foodDto = foodService.updateFoodDetails(id , foodDto);
		FoodDetailsResponse responseDto = FoodDetailsResponse.builder()
				.foodId(foodDto.getFoodId())
				.foodName(foodDto.getFoodName())
				.foodPrice(foodDto.getFoodPrice())
				.foodCategory(foodDto.getFoodCategory())
				.build();
		return responseDto;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{

		OperationStatusModel obj = new OperationStatusModel();
		foodService.deleteFoodItem(id);
		obj.setOperationName("deleteFood");
		obj.setOperationResult("Success");
		return obj;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {

		List<FoodDto> foodDtoList = foodService.getFoods();
		List<FoodDetailsResponse> ans = new ArrayList<>();
		int n = foodDtoList.size();
		for(int i=0; i<n; i++)
		{
			FoodDetailsResponse responseDto = FoodDetailsResponse.builder()
					.foodId(foodDtoList.get(i).getFoodId())
					.foodName(foodDtoList.get(i).getFoodName())
					.foodPrice(foodDtoList.get(i).getFoodPrice())
					.foodCategory(foodDtoList.get(i).getFoodCategory())
					.build();
			ans.add(responseDto);
		}
		return ans;
	}
}
