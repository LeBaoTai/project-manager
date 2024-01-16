package com.lbt.ProjectManger.repositories;

import com.lbt.ProjectManger.domain.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>,
		PagingAndSortingRepository<ProjectEntity, Long> {
}
