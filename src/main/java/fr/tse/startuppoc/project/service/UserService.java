package fr.tse.startuppoc.project.service;

import java.util.List;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.utils.LoginUser;

public interface UserService {
	public List<User> findAllUser();
	public User findById(Long id);
	public User addUser(User user);
	public User login(LoginUser loginUser);
}
