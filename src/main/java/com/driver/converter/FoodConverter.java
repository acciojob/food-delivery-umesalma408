package com.driver.converter;

import com.driver.io.entity.FoodEntity;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.shared.dto.FoodDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FoodConverter {
    public static FoodEntity covertDtoToEntity(FoodDto foodDto){

        return FoodEntity.builder().foodId(foodDto.getFoodId()).foodName(foodDto.getFoodName()).foodCategory(foodDto.getFoodCategory()).build();
    }
    public static FoodDetailsResponse convertEntityToDto(FoodEntity foodEntity) {
        return FoodDetailsResponse.builder().foodId(foodEntity.getFoodId()).foodName(foodEntity.getFoodName()).foodCategory(foodEntity.getFoodCategory()).build();
    }

}

