package com.lbt.ProjectManger.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbt.ProjectManger.TestDataUtil;
import com.lbt.ProjectManger.domain.dto.UserDto;
import com.lbt.ProjectManger.domain.entities.UserEntity;
import com.lbt.ProjectManger.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class TestUseControllerIntegrationTest {

	private UserService userService;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@Autowired
	public TestUseControllerIntegrationTest(UserService userService, MockMvc mockMvc, ObjectMapper objectMapper) {
		this.userService = userService;
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	public void testUserCreateReturnHttpStatusCode201() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		userService.save(userEntity);
		String userJson = objectMapper.writeValueAsString(userEntity);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson)
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		);
	}

	@Test
	public void testUserCreateReturnUserCreated() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		userService.save(userEntity);
		String userJson = objectMapper.writeValueAsString(userEntity);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(userJson)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.username").value("le bao tai")
		);
	}

	@Test
	public void testUserFullUpdateReturnHttpStatusCode200() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		UserEntity savedUser = userService.save(userEntity);
		savedUser.setId(userEntity.getId());
		String jsonUpdateUser = objectMapper.writeValueAsString(savedUser);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/users/" + savedUser.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonUpdateUser)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		);
	}

	@Test
	public void testUserFullUpdateReturnUpdatedUser() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		UserEntity savedUser = userService.save(userEntity);
		savedUser.setId(userEntity.getId());
		savedUser.setPassword("haylam");
		String jsonUpdateUser = objectMapper.writeValueAsString(savedUser);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/users/" + savedUser.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonUpdateUser)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.password").value("haylam")
		);
	}

	@Test
	public void testUserPartialUpdateReturnHttpStatusCode200() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		userService.save(userEntity);

		UserDto userDto = TestDataUtil.createTestUserDto();
		userDto.setPassword("haylam");

		String jsonPartialUpdate = objectMapper.writeValueAsString(userDto);

		mockMvc.perform(
				MockMvcRequestBuilders.patch("/api/users/" + userEntity.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPartialUpdate)
		).andExpect(
				MockMvcResultMatchers.status().isOk()
		);
	}

	@Test
	public void testUserPartialUpdateReturnUpdatedUser() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		userService.save(userEntity);

		UserDto userDto = TestDataUtil.createTestUserDto();
		userDto.setPassword("haylam");

		String jsonPartialUpdate = objectMapper.writeValueAsString(userDto);

		mockMvc.perform(
				MockMvcRequestBuilders.patch("/api/users/" + userEntity.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonPartialUpdate)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.password").value("haylam")
		);
	}

	@Test
	public void testUserDeleteReturnStatusCode204WhenNoUserExisting() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		userService.save(userEntity);

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/users/" + 12)
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isNoContent()
		);
	}

	@Test
	public void testUserDeleteReturnStatusCode204WhenUserExisting() throws Exception {
		UserEntity userEntity = TestDataUtil.createTestUserEntity();
		userService.save(userEntity);

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/users/" + userEntity.getId())
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isNoContent()
		);
	}
}
