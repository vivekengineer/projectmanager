package com.fsd.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.projectmanager.entity.ParentTask;

@Repository
public interface ParentTaskManagerRepository extends JpaRepository<ParentTask,Long>{

}
