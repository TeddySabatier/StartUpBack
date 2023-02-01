package fr.tse.startuppoc.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.startuppoc.project.entity.UserType;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
public class TestUserTypeRepository {
	@Autowired
	UserTypeRepository _userTypeRepository;
	
	@Test
	public void testFindAllusers(){
		List<UserType> users = this._userTypeRepository.findAll();
		assertEquals(3, users.size());		
	}
	
	@Test
	public void testFindById(){
		List<UserType> users = this._userTypeRepository.findAll();
		UserType user = this._userTypeRepository.findById(users.get(0).getId()).orElse(null);
		
		assertEquals(user.getId(), users.get(0).getId());
		assertEquals(user.getName(), users.get(0).getName());
	}
	@Test
	public void testDeleteuser() {
		UserType userType = new UserType();
		userType.setName("Test");
		this._userTypeRepository.save(userType);		
		this._userTypeRepository.delete(userType);
		assertEquals(3,this._userTypeRepository.findAll().size());
	}
	@Test
	public void testAdduser() {
		UserType userType = new UserType();
		userType.setName("Test");
		this._userTypeRepository.save(userType);
		assertEquals(4,this._userTypeRepository.findAll().size());
		this._userTypeRepository.delete(userType);
	}

}
