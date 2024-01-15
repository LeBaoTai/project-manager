package com.lbt.ProjectManger.services;

import com.lbt.ProjectManger.domain.entities.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ProjectService {
	ProjectEntity save(ProjectEntity projectEntity);

	Optional<ProjectEntity> findOne(Long id);

	Page<ProjectEntity> findAll(Pageable pageable);

	ProjectEntity updateProject(Long id, ProjectEntity projectEntity);

	boolean isExists(Long id);

	ProjectEntity partialUpdate(Long id, ProjectEntity projectEntity);

	void delete(Long id);
}
