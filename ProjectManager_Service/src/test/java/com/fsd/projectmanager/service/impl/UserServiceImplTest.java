package com.fsd.projectmanager.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fsd.projectmanager.bo.UserDTO;
import com.fsd.projectmanager.entity.User;
import com.fsd.projectmanager.repository.UserManagerRepository;

public class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserManagerRepository userManagerRepository;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userServiceImpl).build();
		 
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	
	@Test
	public void getAllUsers() {
		List<User> userList=new ArrayList<User>();
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		userList.add(user);
		
		when(userManagerRepository.findAll()).thenReturn(userList);
		 List<UserDTO> pt=userServiceImpl.getAllUsers();
		
	}
	
	@Test
	public void saveUser() {
		UserDTO user=new UserDTO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		userServiceImpl.saveUser(user);
		
	}
	@Test
	public void getUser() {
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		Optional<User> a = Optional.ofNullable(user);
		when(userManagerRepository.findById(Mockito.anyString())).thenReturn(a);
		UserDTO UserDTO=userServiceImpl.getUser("1");
	}

	@Test
	public void updateUser() {
		UserDTO user=new UserDTO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		userServiceImpl.updateUser(user);
	}

}

