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


import fr.tse.startuppoc.project.entity.User;


@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
public class TestUserRepository {
	@Autowired
	UserRepository _userRepository;
	
	@Test
	void testFindAllusers(){
		List<User> users = this._userRepository.findAll();
		assertEquals(3, users.size());		
	}
	
	@Test
	void testFindById(){
		List<User> users = this._userRepository.findAll();
		User user = this._userRepository.findById(users.get(0).getId()).orElse(null);
		
		assertEquals(user.getFirstname(), users.get(0).getFirstname());
		assertEquals(user.getLastname(), users.get(0).getLastname());
		assertEquals(user.getLogin(), users.get(0).getLogin());
		assertEquals(user.getPassword(), users.get(0).getPassword());
		assertEquals(user.getType().getName(), users.get(0).getType().getName());
	}
	@Test
	void testDeleteuser() {
		User user = new User();
		user.setFirstname("Test");
		this._userRepository.save(user);		
		this._userRepository.delete(user);
		assertEquals(3,this._userRepository.findAll().size());
	}
	@Test
	void testAdduser() {
		User user = new User();
		user.setFirstname("Test");
		this._userRepository.save(user);
		assertEquals(4,this._userRepository.findAll().size());
		this._userRepository.delete(user);
	}
	

}
