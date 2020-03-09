package com.fsd.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.projectmanager.bo.TaskDTO;
import com.fsd.projectmanager.entity.ParentTask;
import com.fsd.projectmanager.entity.Project;
import com.fsd.projectmanager.entity.Task;
import com.fsd.projectmanager.entity.User;
import com.fsd.projectmanager.repository.ParentTaskManagerRepository;
import com.fsd.projectmanager.repository.ProjectManagerRepository;
import com.fsd.projectmanager.repository.TaskManagerRepository;
import com.fsd.projectmanager.repository.UserManagerRepository;
import com.fsd.projectmanager.service.ParentTaskService;
import com.fsd.projectmanager.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskManagerRepository taskManagerRepository;
	
	@Autowired
	private ProjectManagerRepository projectManagerRepository;
	
	@Autowired
	private ParentTaskManagerRepository parentManagerRepository;
	
	@Autowired
	private UserManagerRepository userManagerRepository;
	
	@Autowired
	private ParentTaskService parentTaskService;
	
	
	public List<TaskDTO> getAllTasks(){
		
		List<TaskDTO> taskVOList = new ArrayList<TaskDTO>();
		List<Task> tasks =taskManagerRepository.findAll();
		for(Task task: tasks) {
			TaskDTO taskVO=new TaskDTO();
			taskVO.setTaskId(task.getTaskId());
			taskVO.setTaskName(task.getTaskName());
			taskVO.setParentTaskId(task.getParentTask().getParentTaskId());
			taskVO.setParentTaskName(task.getParentTask().getParentTaskName());
			taskVO.setProjectId(task.getProjectDetails().getProjectId());
			taskVO.setPriority(task.getPriority());
			taskVO.setStartDate(task.getStartDate());
			taskVO.setEndDate(task.getEndDate());
			taskVO.setStatus(task.getStatus());
			taskVO.setEmployeeId(task.getUserDetails().getEmployeeId());
			taskVOList.add(taskVO);
			
		}
		return taskVOList;
	}

	public void saveTask(TaskDTO taskVO) {
		
		Task task=new Task();
		task.setEndDate(taskVO.getEndDate());
		task.setPriority(taskVO.getPriority());
		task.setStartDate(taskVO.getStartDate());
		task.setStatus("A");
		task.setTaskName(taskVO.getTaskName());

		ParentTask parent=new ParentTask();
		if(taskVO.getParentTaskId()!=null) {
			parent=parentManagerRepository.findById(taskVO.getParentTaskId()).get();
		}else {
			parent.setParentTaskName(taskVO.getParentTaskName());
			parent.setProjectId(taskVO.getProjectId());
			parentManagerRepository.save(parent);
		}
		task.setParentTask(parent);
		
		Project prjct=new Project();
		prjct=projectManagerRepository.findById(taskVO.getProjectId()).get();
		task.setProjectDetails(prjct);
		
		User usr=new User();
		usr=userManagerRepository.findById(taskVO.getEmployeeId()).get();
		task.setUserDetails(usr);
		taskManagerRepository.save(task);
	}
	
	public TaskDTO getTask(String taskId) {
		Optional<Task> optTask = taskManagerRepository.findById(Long.parseLong(taskId));

		TaskDTO taskVO=new TaskDTO();
		taskVO.setTaskId(optTask.get().getTaskId());
		taskVO.setTaskName(optTask.get().getTaskName());
		taskVO.setParentTaskId(optTask.get().getParentTask().getParentTaskId());
		taskVO.setParentTaskName(optTask.get().getParentTask().getParentTaskName());
		taskVO.setProjectId(optTask.get().getProjectDetails().getProjectId());
		taskVO.setPriority(optTask.get().getPriority());
		taskVO.setStartDate(optTask.get().getStartDate());
		taskVO.setEndDate(optTask.get().getEndDate());
		taskVO.setStatus(optTask.get().getStatus());
		taskVO.setEmployeeId(optTask.get().getUserDetails().getEmployeeId());

		return taskVO;
	}

	public void updateTask(TaskDTO taskVO) {
		Task task=new Task();
		task.setTaskId(taskVO.getTaskId());
		task.setEndDate(taskVO.getEndDate());
		task.setPriority(taskVO.getPriority());
		task.setStartDate(taskVO.getStartDate());
		task.setStatus(taskVO.getStatus());
		task.setTaskName(taskVO.getTaskName());

		ParentTask p= parentTaskService.getParentTasks(taskVO.getParentTaskId());
		//p.setParentTaskId(taskVO.getParentTaskId());
		//p.setParentTaskName("tsk screens");
		task.setParentTask(p);
		
		Project pd= projectManagerRepository.findById(taskVO.getProjectId()).get();
		task.setProjectDetails(pd);
		
		User u=userManagerRepository.findById(taskVO.getEmployeeId()).get();
		task.setUserDetails(u);
		
		taskManagerRepository.save(task);
	}

}
