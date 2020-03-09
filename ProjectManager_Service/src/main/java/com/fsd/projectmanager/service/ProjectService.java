package com.fsd.projectmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsd.projectmanager.bo.ProjectDTO;


@Service
public interface ProjectService {
	
	
	public List<ProjectDTO> getAllProjects();
	
	public void saveProject(ProjectDTO projectVO);
	
	public ProjectDTO getProject(String projectId);
	
	public void updateProject(ProjectDTO projectVO);
	
	
}
