package fr.tse.startuppoc.project.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import net.minidev.json.JSONObject;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class) // Define the order in which tests are executed
class TestTimeDayController {

	@Autowired
	private MockMvc mvc;
	
	@Test
	@Order(1) // First test to be executed
	void getAllTimeDayTest() throws Exception {
		mvc.perform(get("/timeday").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()",is(4)));
	}

	@Test
	@Order(2)
	void getTimeDayByIdTest() throws Exception {
		mvc.perform(get("/timeday/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.date",is("2022-12-13")))
		.andExpect(jsonPath("$.granularity",is(0.5)))
		.andExpect(jsonPath("$.project.id",is(1)))
		.andExpect(jsonPath("$.user.id",is(1)));
	}
	
	@Test
	@Order(3)
	void getTimeDayByUserIdTest() throws Exception {
		mvc.perform(get("/timeday/user/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()",is(2)))
		.andExpect(jsonPath("$[0].id",is(1)))
		.andExpect(jsonPath("$[1].id",is(2)));
	}
	
	@Test
	@Order(4)
	void getTimeDayByProjectIdTest() throws Exception {
		mvc.perform(get("/timeday/project/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()",is(2)))
		.andExpect(jsonPath("$[0].id",is(1)))
		.andExpect(jsonPath("$[1].id",is(3)));
	}
	
	@Test
	@Order(5)
	void postTimeDayTest() throws Exception {
		mvc.perform(get("/timeday").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()",is(4)));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("granularity", 0.2);
		jsonObject.put("date", "2022-12-01");
		
		mvc.perform(post("/timeday").contentType(MediaType.APPLICATION_JSON).content(jsonObject.toJSONString()))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("granularity",is(0.2)))
		.andExpect(jsonPath("date",is("2022-12-01")));
		
		mvc.perform(get("/timeday").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()",is(5)));
	}
	
	@Test
	@Order(6)
	void deleteTimeDayTest() throws Exception {
		mvc.perform(get("/timeday").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()",is(5)));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", 5);
		
		mvc.perform(delete("/timeday").contentType(MediaType.APPLICATION_JSON).content(jsonObject.toJSONString()))
		.andExpect(status().isOk());
		
		mvc.perform(get("/timeday").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.size()",is(4)));
	}
}
