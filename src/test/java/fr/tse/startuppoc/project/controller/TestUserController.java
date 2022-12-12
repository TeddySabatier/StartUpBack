package fr.tse.startuppoc.project.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import net.minidev.json.JSONObject;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@AutoConfigureMockMvc
public class TestUserController {
	
	@Autowired
	private MockMvc mvc;
	@Test
	public void testFindAllUsers() throws Exception {

		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].id",is(1)))
		.andExpect(jsonPath("$[1].id",is(2)))
		.andExpect(jsonPath("$[2].id",is(3)))
		.andExpect(jsonPath("$[0].lastname",is("Dev")))
		.andExpect(jsonPath("$[1].lastname",is("Manager")))
		.andExpect(jsonPath("$[2].lastname",is("Admin")));

	}
	
	@Test
	public void testFindById() throws Exception {

		mvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("id",is(1)))
		.andExpect(jsonPath("lastname",is("Dev")));

	}
	
	@Test
	public void testAddDeveloper() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstname", "testAdd");
		jsonObject.put("lastname", "test");
		jsonObject.put("login", "test");
		jsonObject.put("password", "test");
		
		mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObject.toJSONString()))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("firstname",is("testAdd")));

	}
}
