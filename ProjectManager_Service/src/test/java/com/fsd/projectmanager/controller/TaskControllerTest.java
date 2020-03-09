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

import com.fsd.projectmanager.bo.TaskDTO;
import com.fsd.projectmanager.service.impl.TaskServiceImpl;

public class TaskControllerTest {
	
	@InjectMocks
	TaskController target;
	
	@Mock
	private TaskServiceImpl projectService;
	
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
	public void getTasks() {
		
		List<TaskDTO> tasks =new ArrayList<TaskDTO>();
				
		TaskDTO taskVO=new TaskDTO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		tasks.add(taskVO);
		
		when(projectService.getAllTasks()).thenReturn(tasks);
		List<TaskDTO> tasksList = target.getTasks();
	}
	
	@Test
	public void getTasksById() {
		TaskDTO taskVO=new TaskDTO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		TaskDTO task = target.getTasksById("1L");
		assertEquals("123456",task.getEmployeeId());
		assertEquals("12/08/2018",task.getEndDate());
		task.getParentTaskId();
		assertEquals("parenttask",task.getParentTaskName());
		assertEquals("14",task.getPriority());
		assertEquals("A",task.getStatus());
		task.getTaskId();
		assertEquals("taskname",task.getTaskName());
		
	}
	
	@Test
	public void saveTask() {
		TaskDTO taskVO=new TaskDTO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		boolean status = target.saveTask(taskVO);
		
	}
	
	@Test
	public void updateTask() {
		TaskDTO taskVO=new TaskDTO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		
		boolean status = target.updateTask(taskVO,"1L");
		
	}
	
	@Test
	public void deleteTask() {
		TaskDTO taskVO=new TaskDTO();
		taskVO.setEmployeeId("123456");
		taskVO.setEndDate("12/08/2018");
		taskVO.setParentTaskId(1L);
		taskVO.setParentTaskName("parenttask");
		taskVO.setPriority("14");
		taskVO.setProjectId(1L);
		taskVO.setStartDate("12/08/2018");
		taskVO.setStatus("A");
		taskVO.setTaskId(1L);
		taskVO.setTaskName("taskname");
		when(projectService.getTask(Mockito.anyString())).thenReturn(taskVO);
		boolean status = target.deleteTask("1L");
	}
}
