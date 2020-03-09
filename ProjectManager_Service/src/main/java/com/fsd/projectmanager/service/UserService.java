package com.fsd.projectmanager.service;

import java.util.List;

import com.fsd.projectmanager.bo.UserDTO;

public interface UserService {

public List<UserDTO> getAllUsers();
	
	public void saveUser(UserDTO userVO);
	
	public UserDTO getUser(String userId);
	
	public void updateUser(UserDTO userVO);
}
