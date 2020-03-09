package com.fsd.projectmanager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.projectmanager.bo.ParentTaskDTO;
import com.fsd.projectmanager.entity.ParentTask;
import com.fsd.projectmanager.repository.ParentTaskManagerRepository;
import com.fsd.projectmanager.service.ParentTaskService;

@Service
public class ParentTaskServiceImpl implements ParentTaskService{

	@Autowired
	private ParentTaskManagerRepository parentManagerRepository;
	

	@Override
	public List<ParentTaskDTO> getAllParentTasks() {
		List<ParentTaskDTO> parentTaskVOList = new ArrayList<ParentTaskDTO>();
		List<ParentTask> parentTasks =parentManagerRepository.findAll();
		for(ParentTask parentTask: parentTasks) {
			ParentTaskDTO parentTaskVO=new ParentTaskDTO();
			parentTaskVO.setParentTaskId(parentTask.getParentTaskId());
			parentTaskVO.setParentTaskName(parentTask.getParentTaskName());
			parentTaskVO.setProjectId(parentTask.getProjectId());
			parentTaskVOList.add(parentTaskVO);
			
		}
		return parentTaskVOList;
	}
	
	@Override
	public ParentTask getParentTasks(Long parentId) {
		Optional<ParentTask> optParent = parentManagerRepository.findById(parentId);
		return optParent.get();
	}

	

	
}
