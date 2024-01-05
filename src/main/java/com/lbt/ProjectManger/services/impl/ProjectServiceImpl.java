package com.lbt.ProjectManger.services.impl;

import com.lbt.ProjectManger.domain.entities.ProjectEntity;
import com.lbt.ProjectManger.repositories.ProjectRepository;
import com.lbt.ProjectManger.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public ProjectEntity save(ProjectEntity projectEntity) {
		return projectRepository.save(projectEntity);
	}

	@Override
	public Optional<ProjectEntity> findOne(Long id) {
		return projectRepository.findById(id);
	}

	@Override
	public Page<ProjectEntity> findAll(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}
}