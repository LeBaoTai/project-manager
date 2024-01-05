package com.lbt.ProjectManger.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lbt.ProjectManger.TestDataUtil;
import com.lbt.ProjectManger.domain.entities.ProjectEntity;
import com.lbt.ProjectManger.services.ProjectService;
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
public class TestProjectControllerIntegrationTest {

	private ProjectService projectService;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@Autowired
	public TestProjectControllerIntegrationTest(ProjectService projectService, MockMvc mockMvc, ObjectMapper objectMapper) {
		this.projectService = projectService;
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	public void testProjectCreateReturnHttpStatusCode201() throws Exception {
		ProjectEntity projectEntity = TestDataUtil.createTestProjectEntity();
		ProjectEntity savedProjectEntity = projectService.save(projectEntity);
		String projectJson = objectMapper.writeValueAsString(savedProjectEntity);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects")
						.contentType(MediaType.APPLICATION_JSON)
						.content(projectJson)
		).andExpect(
				MockMvcResultMatchers.status().isCreated()
		);
	}

	@Test
	public void testProjectCreateReturnCreatedProject() throws Exception {
		ProjectEntity projectEntity = TestDataUtil.createTestProjectEntity();
		ProjectEntity savedProjectEntity = projectService.save(projectEntity);
		String projectJson = objectMapper.writeValueAsString(savedProjectEntity);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/projects")
						.contentType(MediaType.APPLICATION_JSON)
						.content(projectJson)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.name").value("Backend Java")
		);
	}

	@Test
	public void testFindOneProjectReturnHttpStatusCode302() throws Exception {
		ProjectEntity projectEntity = TestDataUtil.createTestProjectEntity();
		ProjectEntity savedProjectEntity = projectService.save(projectEntity);
		String projectJson = objectMapper.writeValueAsString(savedProjectEntity);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/projects/" + 1)
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.status().isFound()
		);
	}

	@Test
	public void testFindOneProjectReturnProject() throws Exception {
		ProjectEntity projectEntity = TestDataUtil.createTestProjectEntity();
		ProjectEntity savedProjectEntity = projectService.save(projectEntity);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/projects/" + 1)
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.name").value("Backend Java")
		);
	}

	@Test
	public void testFindManyProjectsReturnProjects() throws Exception {
		ProjectEntity projectEntity = TestDataUtil.createTestProjectEntity();
		ProjectEntity savedProjectEntity = projectService.save(projectEntity);

		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/projects")
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(
				MockMvcResultMatchers.jsonPath("$.content[0].name").value("Backend Java")
		);
	}
}
