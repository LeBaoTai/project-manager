package com.lbt.ProjectManger.domain.entities;

import com.lbt.ProjectManger.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
	private Long id;

	private String title;

	private String description;

	private LocalDateTime start_date;

	private LocalDateTime end_date;

	@Enumerated(EnumType.STRING)
	private Status status;

	private int priority;
}
