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
@Table(name = "projects")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id_seq")
	private Long id;

	private String name;

	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserEntity owner;

	private int members;
}
