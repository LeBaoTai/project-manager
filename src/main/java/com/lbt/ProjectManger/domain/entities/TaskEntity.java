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

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@Enumerated(EnumType.STRING)
	private Status status;

	private int priority;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id")
	private ProjectEntity projectId;
}
