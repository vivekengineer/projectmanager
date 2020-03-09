package com.fsd.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.projectmanager.bo.TaskDTO;
import com.fsd.projectmanager.service.TaskService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@GetMapping("/tasks")
	public List<TaskDTO> getTasks() {
		List<TaskDTO> tasks =taskService.getAllTasks();
		return tasks;
	}
	
	@GetMapping("/tasks/{taskId}")
	public TaskDTO getTasksById(@PathVariable(name="taskId") String taskId) {
		TaskDTO task = taskService.getTask(taskId); 
		return task;
	}
	
	@PostMapping(path = "/tasks", consumes = "application/json", produces = "application/json")
	public boolean saveTask(@RequestBody TaskDTO task) {
		try {
			
			taskService.saveTask(task);
		}catch(Exception e)
		{
			System.out.println("Save Task Failed : " + e.getMessage());
			return false;
		}
		return true;
	}	
	
	@PutMapping("/tasks/{taskId}")
	public boolean updateTask(@RequestBody TaskDTO task, @PathVariable(name="taskId") String taskId) {
		try {
			TaskDTO taskExists = taskService.getTask(taskId);
			if(taskExists != null) {
				taskService.updateTask(task);
			}else {
				System.out.println("updateTask: No task available in the task id : " + taskId);
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Update Task Failed : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	@DeleteMapping("/tasks/{taskId}")
	public boolean deleteTask(@PathVariable(name="taskId") String taskId) {
		try {
			TaskDTO taskRetrived = taskService.getTask(taskId);
			if(taskRetrived != null) {
				taskRetrived.setStatus("I");
				taskService.updateTask(taskRetrived);
			}else {
				System.out.println("deleteTask: No task available in the task id : " + taskId);
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Delete Task Failed : " + e.getMessage());
			return false;
		}
		return true;
	}
}
