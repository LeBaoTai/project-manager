package com.lbt.ProjectManger.domain.dto;

import com.lbt.ProjectManger.domain.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
	private Long id;

	private String title;

	private String Description;

	private LocalDateTime start;

	private LocalDateTime end;

	private Status status;

	private int priority;
}
