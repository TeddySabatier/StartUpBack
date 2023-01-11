package fr.tse.startuppoc.project.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.entity.UserType;
import fr.tse.startuppoc.project.service.UserService;
import fr.tse.startuppoc.project.service.UserTypeService;
import fr.tse.startuppoc.project.utils.Constants;
import fr.tse.startuppoc.project.utils.LoginUser;

@RestController
@CrossOrigin(origins="*",maxAge=3600,methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH,RequestMethod.DELETE})
public class UserController {

	@Autowired
	private UserService _userService;
	
	
	@GetMapping("/users")
	List<User> getAllUsers() {
		return this._userService.findAllUser();
	}
	
	@GetMapping("/users/{id}")
	User getUserById(@PathVariable Long id) {
		return this._userService.findById(id);
	}
	
	@PostMapping("/user")
	User addUser(@Valid @RequestBody User user) {
		return this._userService.addUser(user);		
	}
	
	@PostMapping("/login")
	User signIn(@Valid @RequestBody LoginUser loginUser) {
		return this._userService.login(loginUser);		
	}
	
	@PatchMapping("/switchrole/{id}")
	User switchRole( @PathVariable Long id,@Valid @RequestBody UserType userType) {
		User user=_userService.findById(id);
		switch (userType.getName()) {
		case Constants.NAME_USER_TYPE_DEV: 
			_userService.toUser(user);
			break;
		case Constants.NAME_USER_TYPE_MANAGER: 
			_userService.toManager(user);
			break;
		case Constants.NAME_USER_TYPE_ADMIN: 
			_userService.toAdmin(user);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + userType.getName());
		}
		return _userService.findById(user.getId());
	};
	
	@DeleteMapping("/user")
	void deleteUser(@Valid @RequestBody User user) throws Exception {
		_userService.deleteUser(user);
	};
	
	@PostMapping("/adddeveloper/{id}")
	User addDeveloper(@PathVariable Long id,@Valid @RequestBody User user) throws Exception {
		User Manager = _userService.findById(id);
		_userService.addDeveloper(Manager, user);
		return _userService.findById(Manager.getId());
	};
	
	@GetMapping("/getdevelopers/{id}")
	Set<User> addDeveloper(@PathVariable Long id) throws Exception {
		User User = _userService.findById(id);
		return _userService.findById(User.getId()).getDevelopers();
	};
	
	@PostMapping("/removedeveloper/{id}")
	User removeDeveloper(@PathVariable Long id,@Valid @RequestBody User user) throws Exception {
		User Manager = _userService.findById(id);
		_userService.removeDeveloper(Manager, user);
		return _userService.findById(Manager.getId());
	};
	
	@Transactional
	@PostMapping("/changemanager/{idOldManager}/{idNewManager}")
	void changeManager(@PathVariable Long idOldManager,@PathVariable Long idNewManager, @Valid @RequestBody User Developer) throws Exception {
		User OldManager = _userService.findById(idOldManager);
		User NewManager = _userService.findById(idNewManager);
		User developer=_userService.findById(Developer.getId());
		_userService.removeDeveloper(OldManager, developer);
		_userService.addDeveloper(NewManager, developer);
	};
}
