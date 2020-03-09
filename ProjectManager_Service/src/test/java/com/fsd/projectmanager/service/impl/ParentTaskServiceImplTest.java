package com.fsd.projectmanager.service.impl;

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
import com.fsd.projectmanager.entity.ParentTask;
import com.fsd.projectmanager.repository.ParentTaskManagerRepository;

public class ParentTaskServiceImplTest {
	
	
	@InjectMocks
	private ParentTaskServiceImpl parentSertviceImpl;
	
	@Mock
	private ParentTaskManagerRepository parentManagerRepository;
	
	
	private MockMvc mockMvc;
	
	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(parentSertviceImpl).build();
		 
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	
	@Test
	public void getAllParentTasks() {
		List<ParentTask> parentList=new ArrayList<ParentTask>();
		ParentTask parent=new ParentTask();
		parent.setParentTaskName("parentTaskName");
		parent.setProjectId(1L);
		parent.setParentTaskId(1L);
		parentList.add(parent);
		
		when(parentManagerRepository.findAll()).thenReturn(parentList);
		 List<ParentTaskDTO> pt=parentSertviceImpl.getAllParentTasks();
	}
	

	

}

