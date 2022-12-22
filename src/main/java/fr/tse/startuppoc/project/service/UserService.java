package fr.tse.startuppoc.project.service;

import java.util.List;
import java.util.Set;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.utils.LoginUser;

public interface UserService {
	public List<User> findAllUser();
	public User findById(Long id);
	public User addUser(User user);
	public User login(LoginUser loginUser);
	public void deleteUser(User user);
	public User toUser(User user);
	public User toManager(User user);
	public User toAdmin(User user);
	public Set<User> addDeveloper(User manager,User developer) throws Exception;
	public Set<User> removeDeveloper(User manager,User developer) throws Exception;
}
