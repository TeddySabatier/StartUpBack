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
	
	@Test
	public void testAddDeveloper() {
		User user=new User();
		user.setType(_userTypeService.findById(Constants.ID_USER_TYPE_DEV));
		user=_userService.addUser(user);
		
		User manager=new User();
		manager.setType(_userTypeService.findById(Constants.ID_USER_TYPE_MANAGER));
		manager=_userService.addUser(manager);
		
		User falseManager=new User();
		falseManager.setType(_userTypeService.findById(Constants.ID_USER_TYPE_DEV));
		falseManager=_userService.addUser(falseManager);
		
		User admin=new User();
		admin.setType(_userTypeService.findById(Constants.ID_USER_TYPE_ADMIN));
		admin=_userService.addUser(admin);
		
		try {
			_userService.addDeveloper(manager, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			_userService.addDeveloper(falseManager, user);
		} catch (Exception e) {

		}
		try {
			_userService.addDeveloper(admin, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		assertEquals(_userService.findById(manager.getId()).getDevelopers().size(), 1);
		assertEquals(_userService.findById(admin.getId()).getDevelopers().size(), 1);
		assertEquals(_userService.findById(falseManager.getId()).getDevelopers().size(), 0);
		
		
		_userService.deleteUser(admin);
		_userService.deleteUser(falseManager);
		_userService.deleteUser(manager);
		_userService.deleteUser(user);
	}
	
	@Test
	public void testRemoveDeveloper() {
		User user=new User();
		user.setType(_userTypeService.findById(Constants.ID_USER_TYPE_DEV));
		user=_userService.addUser(user);
		
		User manager=new User();
		manager.setType(_userTypeService.findById(Constants.ID_USER_TYPE_MANAGER));
		manager=_userService.addUser(manager);
		
		User falseManager=new User();
		falseManager.setType(_userTypeService.findById(Constants.ID_USER_TYPE_DEV));
		falseManager=_userService.addUser(falseManager);
		
		User admin=new User();
		admin.setType(_userTypeService.findById(Constants.ID_USER_TYPE_ADMIN));
		admin=_userService.addUser(admin);
		
		try {
			_userService.addDeveloper(manager, user);
			_userService.removeDeveloper(manager, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			_userService.toManager(falseManager);
			falseManager=_userService.findById(falseManager.getId());
			_userService.addDeveloper(falseManager, user);
			_userService.toUser(falseManager);
			falseManager=_userService.findById(falseManager.getId());
			_userService.removeDeveloper(falseManager, user);
		} catch (Exception e) {

		}
		try {
			_userService.addDeveloper(admin, user);
			_userService.removeDeveloper(admin, user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(_userService.findById(manager.getId()).getDevelopers().size(), 0);
		assertEquals(_userService.findById(admin.getId()).getDevelopers().size(), 0);
		assertEquals(_userService.findById(falseManager.getId()).getDevelopers().size(), 1);
		
		
		_userService.deleteUser(admin);
		_userService.deleteUser(falseManager);
		_userService.deleteUser(manager);
		_userService.deleteUser(user);
	}
}
