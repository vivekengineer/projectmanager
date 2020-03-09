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

import com.fsd.projectmanager.bo.TaskDTO;
import com.fsd.projectmanager.entity.ParentTask;
import com.fsd.projectmanager.entity.Project;
import com.fsd.projectmanager.entity.Task;
import com.fsd.projectmanager.entity.User;
import com.fsd.projectmanager.repository.ParentTaskManagerRepository;
import com.fsd.projectmanager.repository.ProjectManagerRepository;
import com.fsd.projectmanager.repository.TaskManagerRepository;
import com.fsd.projectmanager.repository.UserManagerRepository;

public class TaskServiceImplTest {
	
	
	@InjectMocks
	private TaskServiceImpl taskserviceImpl;
	
	@Mock
	private TaskManagerRepository taskManagerRepository;
	
	@Mock
	private ProjectManagerRepository projectManagerRepository;
	
	@Mock
	private ParentTaskManagerRepository parentManagerRepository;
	
	@Mock
	private UserManagerRepository userManagerRepository;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(taskserviceImpl).build();
		 
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void getAllTasks() {
		List<Task> tasks =new ArrayList<Task>();
		
		Task task=new Task();
		task.setEndDate("12/08/2018");
		ParentTask pt=new ParentTask();
		pt.setParentTaskId(1L);
		pt.setParentTaskName("parentTaskName");
		pt.setProjectId(1L);
		task.setParentTask(pt);
		task.setPriority("12");
		Project pd=new Project();
		pd.setEndDate("12/08/2018");
		pd.setManagerId("managerId");
		pd.setPriority("priority");
		pd.setProjectId(1L);
		pd.setProjectName("projectName");
		pd.setStartDate("12/08/2018");
		pd.setStatus("A");
		task.setProjectDetails(pd);
		task.setStartDate("12/08/2018");
		task.setStatus("A");
		task.setTaskId(1L);
		task.setTaskName("taskName");
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		task.setUserDetails(user);
		
		tasks.add(task);
		
		when(taskManagerRepository.findAll()).thenReturn(tasks);
		
		List<TaskDTO> tasksList = taskserviceImpl.getAllTasks();
		
	}
	
	@Test
	public void saveTask() {
		
		TaskDTO TaskDTO=new TaskDTO();
		TaskDTO.setEmployeeId("123456");
		TaskDTO.setEndDate("12/08/2018");
		//TaskDTO.setParentTaskId(1L);
		TaskDTO.setParentTaskName("parenttask");
		TaskDTO.setPriority("14");
		TaskDTO.setProjectId(1L);
		TaskDTO.setStartDate("12/08/2018");
		TaskDTO.setStatus("A");
		TaskDTO.setTaskName("taskname");
		
		ParentTask parent=new ParentTask();
		parent.setParentTaskName("parentTaskName");
		parent.setProjectId(1L);
		//when(parentManagerRepository.findById(Mockito.anyLong()).get()).thenReturn(parent);
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
		User usr=new User();
		usr.setEmployeeId("123456");
		usr.setFirstName("firstName");
		usr.setLastName("lastName");
		usr.setStatus("status");
		Optional<User> u = Optional.ofNullable(usr);
		when(userManagerRepository.findById(Mockito.anyString())).thenReturn(u);
		taskserviceImpl.saveTask(TaskDTO);
		
	}
	

	@Test
	public void getTask() {
		
		Task task=new Task();
		task.setEndDate("12/08/2018");
		ParentTask pt=new ParentTask();
		pt.setParentTaskId(1L);
		pt.setParentTaskName("parentTaskName");
		pt.setProjectId(1L);
		task.setParentTask(pt);
		task.setPriority("12");
		Project pd=new Project();
		pd.setEndDate("12/08/2018");
		pd.setManagerId("managerId");
		pd.setPriority("priority");
		pd.setProjectId(1L);
		pd.setProjectName("projectName");
		pd.setStartDate("12/08/2018");
		pd.setStatus("A");
		task.setProjectDetails(pd);
		task.setStartDate("12/08/2018");
		task.setStatus("A");
		task.setTaskId(1L);
		task.setTaskName("taskName");
		User user=new User();
		user.setEmployeeId("25469");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setStatus("Active");
		task.setUserDetails(user);
		
		Optional<Task> a = Optional.ofNullable(task);
		
		when(taskManagerRepository.findById(Mockito.anyLong())).thenReturn(a);
		TaskDTO TaskDTO=taskserviceImpl.getTask("1");
	}
	
	@Test
	public void updateTask() {
		
		TaskDTO TaskDTO=new TaskDTO();
		TaskDTO.setEmployeeId("123456");
		TaskDTO.setEndDate("12/08/2018");
		TaskDTO.setParentTaskId(1L);
		TaskDTO.setParentTaskName("parenttask");
		TaskDTO.setPriority("14");
		TaskDTO.setProjectId(1L);
		TaskDTO.setStartDate("12/08/2018");
		TaskDTO.setStatus("A");
		TaskDTO.setTaskName("taskname");
		
		
		ParentTask parent=new ParentTask();
		parent.setParentTaskName("parentTaskName");
		parent.setProjectId(1L);
		Optional<ParentTask> p = Optional.ofNullable(parent);
		when(parentManagerRepository.findById(Mockito.anyLong())).thenReturn(p);
		
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
		
		
		User usr=new User();
		usr.setEmployeeId("123456");
		usr.setFirstName("firstName");
		usr.setLastName("lastName");
		usr.setStatus("status");
		Optional<User> u = Optional.ofNullable(usr);
		when(userManagerRepository.findById(Mockito.anyString())).thenReturn(u);
		
		taskserviceImpl.updateTask(TaskDTO);
		
	}
	
	
}

