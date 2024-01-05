package com.lbt.ProjectManger.domain.dto;

import com.lbt.ProjectManger.domain.entities.UserEntity;
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

	private int status;

	private LocalDateTime start;

	private LocalDateTime end;

	private UserDto owner;
}
