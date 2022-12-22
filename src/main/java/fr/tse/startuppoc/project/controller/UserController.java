package fr.tse.startuppoc.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins="*",maxAge=3600,methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
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
	void deleteUser(@Valid @RequestBody User user) {
		_userService.deleteUser(user);
	};
	
	@PostMapping("/adddeveloper/{id}")
	void addDeveloper(@PathVariable Long id,@Valid @RequestBody User user) throws Exception {
		User Manager = _userService.findById(id);
		_userService.addDeveloper(Manager, user);
	};
	
	@PostMapping("/removedeveloper/{id}")
	void removeDeveloper(@PathVariable Long id,@Valid @RequestBody User user) throws Exception {
		User Manager = _userService.findById(id);
		_userService.removeDeveloper(Manager, user);
	};
}
