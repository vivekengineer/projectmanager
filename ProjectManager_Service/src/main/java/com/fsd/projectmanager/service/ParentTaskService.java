package com.fsd.projectmanager.service;

import java.util.List;

import com.fsd.projectmanager.bo.ParentTaskDTO;
import com.fsd.projectmanager.entity.ParentTask;

public interface ParentTaskService {

	public List<ParentTaskDTO> getAllParentTasks();
	public ParentTask getParentTasks(Long parentId);
}
