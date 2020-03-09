package com.fsd.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.projectmanager.bo.UserDTO;
import com.fsd.projectmanager.entity.User;
import com.fsd.projectmanager.repository.UserManagerRepository;
import com.fsd.projectmanager.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserManagerRepository userManagerRepository;
	

	@Override
	public List<UserDTO> getAllUsers() {
		
		List<UserDTO> userVOList = new ArrayList<UserDTO>();
		List<User> users =userManagerRepository.findAll();
		for(User user: users) {
			UserDTO userVO=new UserDTO();
			userVO.setFirstName(user.getFirstName());
			userVO.setLastName(user.getLastName());
			userVO.setEmployeeId(user.getEmployeeId());
			userVO.setStatus(user.getStatus());

			userVOList.add(userVO);
			
		}
		return userVOList;
	
	}

	@Override
	public void saveUser(UserDTO userVO) {
		User user=new User();
		user.setEmployeeId(userVO.getEmployeeId());
		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setStatus(userVO.getStatus());
		
		userManagerRepository.save(user);
		
	}

	@Override
	public UserDTO getUser(String userId) {
		Optional<User> optUser = userManagerRepository.findById(userId);

		UserDTO userVO=new UserDTO();
		userVO.setFirstName(optUser.get().getFirstName());
		userVO.setLastName(optUser.get().getLastName());
		userVO.setEmployeeId(optUser.get().getEmployeeId());
		userVO.setStatus(optUser.get().getStatus());
		return userVO;
	}

	@Override
	public void updateUser(UserDTO userVO) {
		User user=new User();
		user.setEmployeeId(userVO.getEmployeeId());
		user.setFirstName(userVO.getFirstName());
		user.setLastName(userVO.getLastName());
		user.setStatus(userVO.getStatus());
		userManagerRepository.save(user);
		
	}
}
