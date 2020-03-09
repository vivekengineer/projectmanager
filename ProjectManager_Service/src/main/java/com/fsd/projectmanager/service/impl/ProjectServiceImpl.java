package com.fsd.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.projectmanager.bo.ProjectDTO;
import com.fsd.projectmanager.entity.Project;
import com.fsd.projectmanager.repository.ProjectManagerRepository;
import com.fsd.projectmanager.repository.TaskManagerRepository;
import com.fsd.projectmanager.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private TaskManagerRepository taskManagerRepository;
	
	@Autowired
	private ProjectManagerRepository projectManagerRepository;
	
	@Override
	public List<ProjectDTO> getAllProjects() {
		
		List<ProjectDTO> projectVOlist = new ArrayList<ProjectDTO>();
		List<Project> projects =projectManagerRepository.findAll();
		for(Project project: projects) {
			ProjectDTO projectVO=new ProjectDTO();
			projectVO.setProjectId(project.getProjectId());
			projectVO.setProjectName(project.getProjectName());
			projectVO.setStartDate(project.getStartDate());
			projectVO.setEndDate(project.getEndDate());
			projectVO.setPriority(project.getPriority());
			projectVO.setStatus(project.getStatus());
			projectVO.setEmployeeId(project.getManagerId());
			Long task=taskManagerRepository.getNoOfTask(project.getProjectId());
 			projectVO.setNoOfTask(task);

			projectVOlist.add(projectVO);
			
		}
		return projectVOlist;
	}

	@Override
	public void saveProject(ProjectDTO projectVO) {
		
		Project project=new Project();
	
		if(projectVO.getProjectId()!=null) {
			project.setProjectId(projectVO.getProjectId());
		}
		project.setProjectName(projectVO.getProjectName());
		project.setStartDate(projectVO.getStartDate());
		project.setEndDate(projectVO.getEndDate());
		project.setPriority(projectVO.getPriority());
		project.setManagerId(projectVO.getEmployeeId());
		project.setStatus(projectVO.getStatus());
		projectManagerRepository.save(project);
	
		
	}

	@Override
	public ProjectDTO getProject(String projectId) {
		Optional<Project> optProject = projectManagerRepository.findById(Long.parseLong(projectId));

		ProjectDTO projectVO=new ProjectDTO();
		projectVO.setProjectId(optProject.get().getProjectId());
		projectVO.setProjectName(optProject.get().getProjectName());
		projectVO.setStartDate(optProject.get().getStartDate());
		projectVO.setEndDate(optProject.get().getEndDate());
		projectVO.setPriority(optProject.get().getPriority());
		projectVO.setStatus(optProject.get().getStatus());
		projectVO.setEmployeeId(optProject.get().getManagerId());
		return projectVO;
	}

	@Override
	public void updateProject(ProjectDTO projectVO) {

		Project project=new Project();
		project.setProjectId(projectVO.getProjectId());
		project.setProjectName(projectVO.getProjectName());
		project.setStartDate(projectVO.getStartDate());
		project.setEndDate(projectVO.getEndDate());
		project.setPriority(projectVO.getPriority());
		project.setManagerId(projectVO.getEmployeeId());
		projectManagerRepository.save(project);
		
	
		
	}


}
