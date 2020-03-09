package com.fsd.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.projectmanager.bo.UserDTO;
import com.fsd.projectmanager.service.UserService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class UserController {

	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<UserDTO> getusers() {
		List<UserDTO> users =userService.getAllUsers();
		return users;
	}
	
	
	@GetMapping("/users/{userID}")
	public UserDTO getUserById(@PathVariable(name="userID") String userId) {
		UserDTO userVO = userService.getUser(userId); 
		return userVO;
	}
	
	@PostMapping(path = "/users", consumes = "application/json", produces = "application/json")
	public boolean saveUser(@RequestBody UserDTO userVO) {
		try {
			
			userService.saveUser(userVO);
		}catch(Exception e)
		{
			System.out.println("Save Project Failed : " + e.getMessage());
			return false;
		}
		return true;
	}	
	
	@PutMapping("/users/{userID}")
	public boolean updateUser(@RequestBody UserDTO userVO, @PathVariable(name="userID") String userID) {
		try {
			UserDTO userexist = userService.getUser(userID);
			if(userexist != null) {
				userService.updateUser(userexist);
			}else {
				System.out.println("update user: No user available in the user id : " + userID);
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Update user Failed : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	@DeleteMapping("/users/{userID}")
	public boolean deleteUser(@PathVariable(name="userID") String userID) {
		try {
			UserDTO userexist = userService.getUser(userID);
			if(userexist != null) {
				userexist.setStatus("I");
				userService.updateUser(userexist);
			}else {
				System.out.println("delete user: No user available in the user id : " + userID);
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Delete user Failed : " + e.getMessage());
			return false;
		}
		return true;
	}
}
