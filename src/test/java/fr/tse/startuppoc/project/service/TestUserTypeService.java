package fr.tse.startuppoc.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
public class TestUserTypeService {
	@Autowired
	UserTypeService _userTypeService;
	
	@Test
	public void testFindAllUserType() {
		assertEquals(3, _userTypeService.findAllUserType().size());
	}
	
	@Test
	public void testFindById() {		
		assertEquals("Developer", _userTypeService.findById(_userTypeService.findAllUserType().get(0).getId()).getName());
	}

}
