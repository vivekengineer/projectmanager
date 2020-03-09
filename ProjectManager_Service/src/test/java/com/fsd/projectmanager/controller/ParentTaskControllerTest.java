package com.fsd.projectmanager.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fsd.projectmanager.bo.ParentTaskDTO;
import com.fsd.projectmanager.service.impl.ParentTaskServiceImpl;

public class ParentTaskControllerTest {
	@InjectMocks
	ParentTaskController target;
	
	@Mock
	private ParentTaskServiceImpl projectService;
	
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
	public void getParentTasks() {
		
		List<ParentTaskDTO> parentList=new ArrayList<ParentTaskDTO>();
		ParentTaskDTO parent=new ParentTaskDTO();
		parent.setParentTaskId(1L);
		parent.setParentTaskName("parent1");
		parent.setProjectId(1L);
		
		
		when(projectService.getAllParentTasks()).thenReturn(parentList);
		List<ParentTaskDTO> tasks =target.getParentTasks();
		
	}
}
