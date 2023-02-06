package fr.tse.startuppoc.project.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
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
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.tse.startuppoc.project.utils.Constants;
import net.minidev.json.JSONObject;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@AutoConfigureMockMvc
@CrossOrigin("*")
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
	public void testAddUser() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstname", "testAdd");
		jsonObject.put("lastname", "test");
		jsonObject.put("login", "test");
		jsonObject.put("password", "test");
		
		mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObject.toJSONString()))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("firstname",is("testAdd")));
==== BASE ====
==== BASE ====

	}
	
	@Test
	public void testDeleteUser() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("firstname", "testAdd");
		jsonObject.put("lastname", "test");
		jsonObject.put("login", "test");
		jsonObject.put("password", "test1");
		jsonObject.put("type", null);
		
		mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObject.toJSONString()))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("firstname",is("testAdd")));
		
		jsonObject.put("id", 5);
		
		mvc.perform(delete("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObject.toJSONString()))
		.andExpect(status().isOk());
		
		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()",is(3)));

	}
	
	@Test
	public void testChangeManager() throws Exception {
		JSONObject jsonObjectUser = new JSONObject();
		jsonObjectUser.put("firstname", "testAdd");
		jsonObjectUser.put("lastname", "test");
		jsonObjectUser.put("login", "test");
		jsonObjectUser.put("password", "test1");
		JSONObject jsonObjectDeveloperType=new JSONObject();
		jsonObjectDeveloperType.put("id", Constants.ID_USER_TYPE_DEV);
		jsonObjectDeveloperType.put("name", Constants.NAME_USER_TYPE_DEV);
		jsonObjectUser.put("type", jsonObjectDeveloperType);

		
		mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectUser.toJSONString()))
		.andExpect(status().isOk());
		
		jsonObjectUser.put("id", 6);
		
		JSONObject jsonObjectManagerType=new JSONObject();
		jsonObjectManagerType.put("id", Constants.ID_USER_TYPE_MANAGER);
		jsonObjectManagerType.put("name", Constants.NAME_USER_TYPE_MANAGER);
		
		JSONObject jsonObjectNewManager= new JSONObject();
		jsonObjectNewManager.put("firstname", "testAdd");
		jsonObjectNewManager.put("lastname", "test");
		jsonObjectNewManager.put("login", "testNewManager");
		jsonObjectNewManager.put("password", "test1");
		jsonObjectNewManager.put("type", jsonObjectManagerType);
		
		mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectNewManager.toJSONString()))
		.andExpect(status().isOk());
		
		jsonObjectNewManager.put("id", 7);
		
		JSONObject jsonObjectOldManager= new JSONObject();
		jsonObjectOldManager.put("firstname", "testAdd");
		jsonObjectOldManager.put("lastname", "test");
		jsonObjectOldManager.put("login", "testNewManager");
		jsonObjectOldManager.put("password", "test1");
		jsonObjectOldManager.put("type", jsonObjectManagerType);
		
		mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectOldManager.toJSONString()))
		.andExpect(status().isOk());
		
		jsonObjectOldManager.put("id", 8);
		
		mvc.perform(post("/adddeveloper/8").contentType(MediaType.APPLICATION_JSON).content(jsonObjectUser.toJSONString()))
		.andExpect(status().isOk());

		
		mvc.perform(post("/changemanager/8/7").contentType(MediaType.APPLICATION_JSON).content(jsonObjectUser.toJSONString()))
		.andExpect(status().isOk());
		

		mvc.perform(get("/users/7").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())	
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("developers.length()",is(1)));
		


		mvc.perform(delete("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectUser.toJSONString()))
		.andExpect(status().isOk());
		mvc.perform(delete("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectNewManager.toJSONString()))
		.andExpect(status().isOk());
		mvc.perform(delete("/user").contentType(MediaType.APPLICATION_JSON).content(jsonObjectOldManager.toJSONString()))
		.andExpect(status().isOk());
	
		
		mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()",is(3)));

	}
	
}
