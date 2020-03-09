package com.fsd.projectmanager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fsd.projectmanager.bo.ProjectDTO;
import com.fsd.projectmanager.bo.TaskDTO;
import com.fsd.projectmanager.service.impl.ProjectServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectManagerControllerTest {
	
	@InjectMocks
	ProjectManagerController target;
	
	@Mock
	private ProjectServiceImpl projectService;
	
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
	public void getProjects() {
		List<ProjectDTO> projectList =new ArrayList<ProjectDTO>();
		ProjectDTO project=new ProjectDTO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		projectList.add(project);
		when(projectService.getAllProjects()).thenReturn(projectList);
		List<ProjectDTO> tasks =target.getProjects();
		
	}
	
	@Test
	public void getProjectById() {
		ProjectDTO project=new ProjectDTO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		ProjectDTO tasks =target.getProjectById("1");
		
		
		assertEquals("298960",project.getEmployeeId());
		assertEquals("12/08/2018",project.getEndDate());
		project.getNoOfTask();
		assertEquals("12",project.getPriority());
		project.getProjectId();
		
		assertEquals("Ebiz",project.getProjectName());
		assertEquals("12/08/2018",project.getStartDate());
		assertEquals("Active",project.getStatus());
		
	}
	
	
	@Test
	public void saveProject() {
		ProjectDTO project=new ProjectDTO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		boolean status=target.saveProject(project);
	}
	
	
	@Test
	public void updateProject() {
		ProjectDTO project=new ProjectDTO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		
		boolean status = target.updateProject(project,"1L");
		
	}
	
	@Test
	public void deleteprojects() {
		TaskDTO taskVO=new TaskDTO();
		ProjectDTO project=new ProjectDTO();
		project.setEmployeeId("298960");
		project.setEndDate("12/08/2018");
		project.setNoOfTask(1L);
		project.setPriority("12");
		project.setProjectId(1L);
		project.setProjectName("Ebiz");
		project.setStartDate("12/08/2018");
		project.setStatus("Active");
		
		when(projectService.getProject(Mockito.anyString())).thenReturn(project);
		boolean status = target.deleteprojects("1L");
	}
	
	
	
}
