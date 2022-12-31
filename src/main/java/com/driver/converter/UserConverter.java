package com.driver.converter;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.UserEntity;
import com.driver.model.response.UserResponse;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.UserDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

public static UserEntity convertDtoTOEntity(UserDto userDto){
    return UserEntity.builder().userId(userDto.getUserId()).firstName(userDto.getFirstName()).lastName(userDto.getLastName()).email(userDto.getEmail()).build();
}
public static UserResponse convertEntityTODto(UserEntity userEntity){
    return UserResponse.builder().userId(userEntity.getUserId()).firstName(userEntity.getFirstName()).lastName(userEntity.getLastName()).build();
}

}
