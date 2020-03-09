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

import com.fsd.projectmanager.bo.ProjectDTO;
import com.fsd.projectmanager.service.impl.ProjectServiceImpl;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ProjectManagerController {
	@Autowired
	private ProjectServiceImpl projectManagerService;
	
	@GetMapping("/projects")
	public List<ProjectDTO> getProjects() {
		List<ProjectDTO> tasks =projectManagerService.getAllProjects();
		return tasks; 
	}
	
	
	@GetMapping("/projects/{projectsId}")
	public ProjectDTO getProjectById(@PathVariable(name="projectsId") String projectsId) {
		ProjectDTO projectVO = projectManagerService.getProject(projectsId); 
		return projectVO;
	}
	
	
	@PostMapping(path = "/projects", consumes = "application/json", produces = "application/json")
	public boolean saveProject(@RequestBody ProjectDTO projectVO) {
		try {
			
			projectManagerService.saveProject(projectVO);
		}catch(Exception e)
		{
			System.out.println("Save Project Failed : " + e.getMessage());
			return false;
		}
		return true;
	}	
	
	@PutMapping("/projects/{projectID}")
	public boolean updateProject(@RequestBody ProjectDTO projectVO, @PathVariable(name="projectID") String projectId) {
		try {
			ProjectDTO projectexist = projectManagerService.getProject(projectId);
			if(projectexist != null) {
				projectManagerService.updateProject(projectVO);
			}else {
				System.out.println("updateProject: No project available in the project id : " + projectId);
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Update Project Failed : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	@DeleteMapping("/projects/{projectsId}")
	public boolean deleteprojects(@PathVariable(name="projectsId") String projectsId) {
		try {
			ProjectDTO projectVO = projectManagerService.getProject(projectsId);
			if(projectVO != null) {
				projectVO.setStatus("completed");
				projectManagerService.updateProject(projectVO);
			}else {
				System.out.println("deleteproject: No project available in the project id : " + projectsId);
				return false;
			}
		}catch(Exception e)
		{
			System.out.println("Delete project Failed : " + e.getMessage());
			return false;
		}
		return true;
	}
	
	
}
