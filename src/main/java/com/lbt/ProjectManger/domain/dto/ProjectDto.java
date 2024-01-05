package com.lbt.ProjectManger.domain.dto;

import com.lbt.ProjectManger.domain.entities.UserEntity;
import com.lbt.ProjectManger.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto {
	private Long id;

	private String name;

	private String description;

	private Status status;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	private UserDto owner;

	private int members;
}
