package fr.tse.startuppoc.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.service.UserService;
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
	User addProject(@Valid @RequestBody User user) {
		return this._userService.addUser(user);		
	}
	
	@PostMapping("/login")
	User signIn(@Valid @RequestBody LoginUser loginUser) {
		return this._userService.login(loginUser);		
	}
}
