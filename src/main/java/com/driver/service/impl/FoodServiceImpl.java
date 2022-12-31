package com.driver.service.impl;

import com.driver.converter.FoodConverter;
import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodRepository foodRepository;
@Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity f = FoodEntity.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodCategory(food.getFoodCategory())
                .build();
        foodRepository.save(f);
        return food;
        }

       @Override
     public FoodDto getFoodById(String foodId) throws Exception{
           FoodEntity foodEntity =  foodRepository.findByFoodId(foodId);
           FoodDto foodDto = FoodDto.builder()
                   .id(foodEntity.getId())
                   .foodId(foodEntity.getFoodId())
                   .foodName(foodEntity.getFoodName())
                   .foodCategory(foodEntity.getFoodCategory())
                   .foodPrice(foodEntity.getFoodPrice())
                   .build();
           return foodDto;


    }
    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception{
        FoodEntity oldFood = foodRepository.findByFoodId(foodId);
        FoodEntity newFood = FoodEntity.builder()
                .id(oldFood.getId())
                .foodId(foodId)
                .foodName(foodDetails.getFoodName())
                .foodPrice(foodDetails.getFoodPrice())
                .foodCategory(foodDetails.getFoodCategory())
                .build();
        foodRepository.save(newFood);
        return foodDetails;
    }
    @Override
    public void deleteFoodItem(String id) throws Exception{
        foodRepository.deleteById(Long.valueOf(id));
    }
    @Override
    public List<FoodDto> getFoods(){

        List<FoodEntity> food = (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> ans = new ArrayList<>();
        for(int i = 0; i<food.size(); i++)
        {
            FoodDto fdto = FoodDto.builder()
                    .id(food.get(i).getId())
                    .foodId(food.get(i).getFoodId())
                    .foodName(food.get(i).getFoodName())
                    .foodCategory(food.get(i).getFoodCategory())
                    .foodPrice(food.get(i).getFoodPrice())
                    .build();
            ans.add(fdto);
        }
        return ans;
    }
}