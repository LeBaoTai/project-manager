package com.lbt.ProjectManger.controllers;

import com.lbt.ProjectManger.domain.dto.ProjectDto;
import com.lbt.ProjectManger.domain.entities.ProjectEntity;
import com.lbt.ProjectManger.mappers.Mapper;
import com.lbt.ProjectManger.services.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ProjectController {
	private ProjectServiceImpl projectService;
	private Mapper<ProjectEntity, ProjectDto> projectMapper;

	@Autowired
	public ProjectController(ProjectServiceImpl projectService, Mapper<ProjectEntity, ProjectDto> projectMapper) {
		this.projectService = projectService;
		this.projectMapper = projectMapper;
	}

	@PostMapping(path = "/projects")
	public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
		ProjectEntity projectEntity = projectMapper.mapFrom(projectDto);
		ProjectEntity savedProjectEntity = projectService.save(projectEntity);
		return new ResponseEntity<>(projectMapper.mapTo(savedProjectEntity), HttpStatus.CREATED);
	}

	@GetMapping(path = "/projects/{id}")
	public ResponseEntity<ProjectDto> findOneProject(@PathVariable("id") Long id) {
		Optional<ProjectEntity> projectEntity = projectService.findOne(id);
		return projectEntity.map(foundProject -> {
			ProjectDto projectDto = projectMapper.mapTo(foundProject);
			return new ResponseEntity<>(projectDto, HttpStatus.FOUND);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(path = "/projects")
	public Page<ProjectDto> findManyProject(Pageable pageable) {
		Page<ProjectEntity> projects = projectService.findAll(pageable);
		return projects.map(projectMapper::mapTo);
	}
}
