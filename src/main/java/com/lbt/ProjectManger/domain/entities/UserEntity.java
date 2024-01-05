package com.lbt.ProjectManger.domain.entities;

import com.lbt.ProjectManger.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	private Long id;

	private String username;

	private String password;

	private String email;

	private Role role;
}
