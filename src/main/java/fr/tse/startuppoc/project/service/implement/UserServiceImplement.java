package fr.tse.startuppoc.project.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.repository.UserRepository;
import fr.tse.startuppoc.project.service.UserService;
import fr.tse.startuppoc.project.utils.LoginUser;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserRepository _userRepository;
	
	@Override
	public List<User> findAllUser() {
		return this._userRepository.findAll();
	}

	@Override
	public User findById(Long id) {
		return this._userRepository.findById(id).orElse(null);
	}

	@Override
	public User addUser(User user) {
		return this._userRepository.save(user);
	}
	
	@Override
	public User login(LoginUser loginUser) {
		List<User> allUser = this._userRepository.findAll();
		for (User user : allUser) {
			if (user.getLogin().equals(loginUser.getLogin()) && user.getPassword().equals(loginUser.getPassword())) return user;
		}
		return null;
	}
	
	@Override
	public void deleteUser(User user) {
		_userRepository.delete(user);
		
	}
}
