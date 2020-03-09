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

import com.fsd.projectmanager.bo.ProjectDTO;
import com.fsd.projectmanager.entity.Project;
import com.fsd.projectmanager.repository.ProjectManagerRepository;

public class ProjectServiceImplTest {
	
	@InjectMocks
	private ProjectServiceImpl projectServiceImpl;
	
	@Mock
	private ProjectManagerRepository projectManagerRepository;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectServiceImpl).build();
		 
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	

	@Test
	public void getAllProjects() {
		List<Project> prjctList=new ArrayList<Project>();
		Project prjct=new Project();
		prjct.setEndDate("12/08/2018");
		prjct.setManagerId("123456");
		prjct.setPriority("14");
		prjct.setProjectId(1L);
		prjct.setProjectName("projectName");
		prjct.setStartDate("12/08/2018");
		prjct.setStatus("A");
		prjctList.add(prjct);
		when(projectManagerRepository.findAll()).thenReturn(prjctList);
		List<ProjectDTO> prjctVO=projectServiceImpl.getAllProjects();
		
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
		
		projectServiceImpl.saveProject(project);
	}
	
	@Test
	public void getProject() {
		Project prjct=new Project();
		prjct.setEndDate("12/08/2018");
		prjct.setManagerId("123456");
		prjct.setPriority("14");
		prjct.setProjectId(1L);
		prjct.setProjectName("projectName");
		prjct.setStartDate("12/08/2018");
		prjct.setStatus("A");
		Optional<Project> a = Optional.ofNullable(prjct);
		when(projectManagerRepository.findById(Mockito.anyLong())).thenReturn(a);
		ProjectDTO ProjectDTO=projectServiceImpl.getProject("1");
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
		projectServiceImpl.updateProject(project);
	}
	

}

