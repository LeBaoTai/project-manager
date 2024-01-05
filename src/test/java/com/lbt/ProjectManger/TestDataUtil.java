package com.lbt.ProjectManger;

import com.lbt.ProjectManger.domain.enums.Role;
import com.lbt.ProjectManger.domain.dto.UserDto;
import com.lbt.ProjectManger.domain.entities.UserEntity;

public class TestDataUtil {
	public static UserDto createTestUserDTO() {
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
}
