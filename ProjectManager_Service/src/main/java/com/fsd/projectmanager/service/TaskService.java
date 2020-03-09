package com.fsd.projectmanager.service;

import java.util.List;

import com.fsd.projectmanager.bo.TaskDTO;

public interface TaskService {

	public List<TaskDTO> getAllTasks();
	
	public void saveTask(TaskDTO task);
	
	public TaskDTO getTask(String taskId);
	
	public void updateTask(TaskDTO taskVO);
}
