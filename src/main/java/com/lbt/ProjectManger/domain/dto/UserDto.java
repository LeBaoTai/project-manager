package com.lbt.ProjectManger.domain.dto;

import com.lbt.ProjectManger.domain.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private Long id;

	private String username;

	private String password;

	private String email;

	private Role role;
}
