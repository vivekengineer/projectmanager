package com.fsd.projectmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import com.fsd.projectmanager.service.impl.UserServiceImpl;

public class UserControllerTest {
	
	@InjectMocks
	UserController target;
	
	@Mock
	private UserServiceImpl userService;
	
	private MockMvc mockMvc;

	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(target).build();
		 
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void getusers() {
		List<UserDTO> userList =new ArrayList<UserDTO>();
		UserDTO user=new UserDTO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		userList.add(user);
		when(userService.getAllUsers()).thenReturn(userList);
		List<UserDTO> users =target.getusers();
		
	}
	
	@Test
	public void getUserById() {
		UserDTO user=new UserDTO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		when(userService.getUser(Mockito.anyString())).thenReturn(user);
		UserDTO userVO =target.getUserById("1");
		
		assertEquals("25469",user.getEmployeeId());
		assertEquals("fname",user.getFirstName());
		assertEquals("lname",user.getLastName());
		assertEquals("Active",user.getStatus());
	}
	
	@Test
	public void saveUser() {
		UserDTO user=new UserDTO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		boolean status=target.saveUser(user);
	}
	
	@Test
	public void updateUser() {
		UserDTO user=new UserDTO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		when(userService.getUser(Mockito.anyString())).thenReturn(user);
		
		boolean status = target.updateUser(user,"1L");
		
	}
	
	@Test
	public void deleteUser() {
		UserDTO user=new UserDTO();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		
		when(userService.getUser(Mockito.anyString())).thenReturn(user);
		boolean status = target.deleteUser("1L");
	}
}
