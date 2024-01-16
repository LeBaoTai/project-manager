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
	public ProjectEntity updateProject(Long id ,ProjectEntity projectEntity) {
		projectEntity.setId(id);
		return projectRepository.save(projectEntity);
	}
	@Override
	public ProjectEntity partialUpdate(Long id, ProjectEntity projectEntity) {
		projectEntity.setId(id);

		return projectRepository.findById(id).map(existingProject -> {
			Optional.ofNullable(projectEntity.getName()).ifPresent(existingProject::setName);
			Optional.ofNullable(projectEntity.getDescription()).ifPresent(existingProject::setDescription);
			Optional.ofNullable(projectEntity.getStatus()).ifPresent(existingProject::setStatus);
			Optional.ofNullable(projectEntity.getStartDate()).ifPresent(existingProject::setStartDate);
			Optional.ofNullable(projectEntity.getEndDate()).ifPresent(existingProject::setEndDate);
			Optional.ofNullable(projectEntity.getMembers()).ifPresent(existingProject::setMembers);
			return projectRepository.save(existingProject);
		}).orElseThrow(() -> new RuntimeException("Not found Project"));
	}

	@Override
	public boolean isExists(Long id) {
		return projectRepository.existsById(id);
	}


	@Override
	public Page<ProjectEntity> findAll(Pageable pageable) {
		return projectRepository.findAll(pageable);
	}

	@Override
	public void delete(Long id) {
		projectRepository.deleteById(id);
	}
}