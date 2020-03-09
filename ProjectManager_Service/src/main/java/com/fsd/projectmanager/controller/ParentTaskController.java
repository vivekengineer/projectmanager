package com.fsd.projectmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.projectmanager.bo.ParentTaskDTO;
import com.fsd.projectmanager.service.ParentTaskService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ParentTaskController {
	
	@Autowired
	private ParentTaskService parentTaskService;


	@GetMapping("/ParentTasks")
	public List<ParentTaskDTO> getParentTasks() {
		List<ParentTaskDTO> tasks =parentTaskService.getAllParentTasks();
		return tasks;
	}
	
}
