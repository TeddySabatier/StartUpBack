package fr.tse.startuppoc.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.utils.Constants;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
public class TestUserService {

	@Autowired
	UserService _userService;
	

	@Autowired
	UserTypeService _userTypeService;
	
	@Test
	public void testFindAllUser() {
		assertEquals(3, _userService.findAllUser().size());
	}
	
	@Test
	public void testFindById() {
		
		assertEquals("Dev", _userService.findById(_userService.findAllUser().get(0).getId()).getLastname());
	}
	
	@Test
	public void testAddUser() {
		User user=new User();
		user.setFirstname("test");
		_userService.addUser(user);
		assertEquals(4, _userService.findAllUser().size());
		_userService.deleteUser(user);
	}
	
	@Test
	public void testDeleteUser() {
		User user=new User();
		user.setFirstname("test");
		_userService.addUser(user);
		_userService.deleteUser(user);
		assertEquals(3, _userService.findAllUser().size());
	}
	
	@Test
	public void testToUser() {
		User user=new User();
		user.setType(_userTypeService.findById(Constants.ID_USER_TYPE_ADMIN));
		_userService.addUser(user);
		_userService.toUser(user);
		assertEquals(Constants.ID_USER_TYPE_DEV, _userService.findById(user.getId()).getType().getId());
		_userService.deleteUser(user);
	}
	
	@Test
	public void testToManager() {
		User user=new User();
		user.setType(_userTypeService.findById(Constants.ID_USER_TYPE_DEV));
		_userService.addUser(user);
		_userService.toManager(user);
		assertEquals(Constants.ID_USER_TYPE_MANAGER, _userService.findById(user.getId()).getType().getId());
		_userService.deleteUser(user);
	}
	
	@Test
	public void testToAdmin() {
		User user=new User();
		user.setType(_userTypeService.findById(Constants.ID_USER_TYPE_DEV));
		_userService.addUser(user);
		_userService.toAdmin(user);
		assertEquals(Constants.ID_USER_TYPE_ADMIN, _userService.findById(user.getId()).getType().getId());
		_userService.deleteUser(user);
	}
}
