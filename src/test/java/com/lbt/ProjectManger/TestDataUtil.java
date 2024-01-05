package com.lbt.ProjectManger;

import com.lbt.ProjectManger.domain.dto.ProjectDto;
import com.lbt.ProjectManger.domain.entities.ProjectEntity;
import com.lbt.ProjectManger.domain.enums.Role;
import com.lbt.ProjectManger.domain.dto.UserDto;
import com.lbt.ProjectManger.domain.entities.UserEntity;
import com.lbt.ProjectManger.domain.enums.Status;

import java.time.LocalDateTime;

public class TestDataUtil {
	public static UserDto createTestUserDto() {
		return UserDto.builder()
				.id(1L)
				.username("le bao tai")
				.password("1234")
				.email("lebaota@gmail.com")
				.role(Role.valueOf("USER"))
				.build();
	}
	public static UserEntity createTestUserEntity() {
		return UserEntity.builder()
				.id(1L)
				.username("le bao tai")
				.password("1234")
				.email("lebaota@gmail.com")
				.role(Role.valueOf("USER"))
				.build();
	}

	public static ProjectDto createTestProjecTDto() {
		return ProjectDto.builder()
				.id(1L)
				.name("Backend Java")
				.description("Use Spring Boot")
				.startDate(LocalDateTime.parse("2024-02-02T12:59:11"))
				.endDate(LocalDateTime.parse("2024-02-05T12:59:11"))
				.status(Status.valueOf("WAITING"))
				.owner(null)
				.build();
	}
	public static ProjectEntity createTestProjectEntity () {
		return ProjectEntity.builder()
				.id(1L)
				.name("Backend Java")
				.description("Use Spring Boot")
				.startDate(LocalDateTime.parse("2024-02-02T12:59:11"))
				.endDate(LocalDateTime.parse("2024-02-05T12:59:11"))
				.status(Status.valueOf("WAITING"))
				.owner(null)
				.build();
	}
}
