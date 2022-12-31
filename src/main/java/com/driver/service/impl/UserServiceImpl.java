package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception{
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        userRepository.save(userEntity);
        return user;
    }
    @Override
   public UserDto getUser(String email) throws Exception{
        UserEntity userEntity = userRepository.findByEmail(email);
        UserDto userDto = UserDto.builder()
                .userId(userEntity.getUserId())
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();

        return userDto;
    }
    @Override
   public UserDto getUserByUserId(String userId) throws Exception{
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = UserDto.builder()
                .userId(userEntity.getUserId())
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();
        return userDto;
    }
    @Override
   public UserDto updateUser(String userId, UserDto user) throws Exception{
        UserEntity oldUser = userRepository.findByUserId(userId);
        UserEntity newUser = UserEntity.builder()
                .id(oldUser.getId())
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        userRepository.save(newUser);
        return user;
    }
    @Override
    public void deleteUser(String userId) throws Exception{
        userRepository.deleteById(Long.valueOf(userId));
    }
    @Override
    public List<UserDto> getUsers(){
        List<UserEntity> userEntityList = (List<UserEntity>)userRepository.findAll();
        List<UserDto> ans = new ArrayList<>();
        int n = userEntityList.size();
        for(int i = 0; i<n; i++)
        {
            UserDto userDto = UserDto.builder()
                    .userId(userEntityList.get(i).getUserId())
                    .id(userEntityList.get(i).getId())
                    .firstName(userEntityList.get(i).getFirstName())
                    .lastName(userEntityList.get(i).getLastName())
                    .email(userEntityList.get(i).getEmail())
                    .build();
            ans.add(userDto);
        }
        return ans;
    }
}