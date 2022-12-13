package fr.tse.startuppoc.project.service.implement;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.repository.UserRepository;
import fr.tse.startuppoc.project.repository.UserTypeRepository;
import fr.tse.startuppoc.project.service.UserService;
import fr.tse.startuppoc.project.utils.Constants;
import fr.tse.startuppoc.project.utils.LoginUser;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	private UserRepository _userRepository;
	
	@Autowired
	private UserTypeRepository _userTypeRepository;
	
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

	
	@Override
	@Transactional
	public User toUser(User user) {
		User temp=_userRepository.findById(user.getId()).orElse(null);
		_userRepository.save(temp);
		temp.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_DEV).orElse(null));
		return temp;
	}

	@Override
	@Transactional
	public User toManager(User user) {
		User temp=_userRepository.findById(user.getId()).orElse(null);
		_userRepository.save(temp);
		temp.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_MANAGER).orElse(null));
		return temp;
	}

	@Override
	@Transactional
	public User toAdmin(User user) {
		User temp=_userRepository.findById(user.getId()).orElse(null);
		_userRepository.save(temp);
		temp.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_ADMIN).orElse(null));
		return temp;
	}
	
}
