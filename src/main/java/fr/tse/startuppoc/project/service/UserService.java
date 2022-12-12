package fr.tse.startuppoc.project.service;

import java.util.List;

import javax.validation.Valid;

import fr.tse.startuppoc.project.entity.User;

public interface UserService {
	public List<User> findAllUser();
	public User findById(Long id);
	public User addUser(@Valid User user);
	public void deleteUser(User user);
}
