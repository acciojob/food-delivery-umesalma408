package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
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
@RequestMapping("/users")
public class UserController {
@Autowired
	UserServiceImpl userService;
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{

		UserDto userDto = userService.getUserByUserId(id);
		UserResponse responseDto = UserResponse.builder()
				.userId(userDto.getUserId())
				.email(userDto.getEmail())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.build();
		return responseDto;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{

		UserDto userDto = UserDto.builder()
				.firstName(userDetails.getFirstName())
				.lastName(userDetails.getLastName())
				.email(userDetails.getEmail())
				.build();
		UserResponse responseDto = UserResponse.builder()
				.userId(userDto.getUserId())
				.email(userDto.getEmail())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.build();
		return responseDto;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{

		UserDto userDto =  UserDto.builder()
				.firstName(userDetails.getFirstName())
				.lastName(userDetails.getLastName())
				.email(userDetails.getEmail())
				.build();
		userDto = userService.updateUser(id , userDto);
		UserResponse responseDto = UserResponse.builder()
				.userId(userDto.getUserId())
				.email(userDto.getEmail())
				.firstName(userDto.getFirstName())
				.lastName(userDto.getLastName())
				.build();
		return responseDto;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{

		userService.deleteUser(id);
		OperationStatusModel obj=new OperationStatusModel();
		obj.setOperationName("deleteUser");
		obj.setOperationResult("Success");
		return obj;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){

		List<UserDto> userDtoList = userService.getUsers();
		List<UserResponse> ans = new ArrayList<>();
		int n = userDtoList.size();
		for(int i=0; i<n; i++)
		{
			UserResponse responseDto = UserResponse.builder()
					.userId(userDtoList.get(i).getUserId())
					.email(userDtoList.get(i).getEmail())
					.firstName(userDtoList.get(i).getFirstName())
					.lastName(userDtoList.get(i).getLastName())
					.build();
			ans.add(responseDto);
		}
		return ans;
	}
	
}
