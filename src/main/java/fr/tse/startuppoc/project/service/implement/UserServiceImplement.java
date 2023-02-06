package fr.tse.startuppoc.project.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.repository.TimeDayRepository;
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
	
	@Autowired
	private TimeDayRepository _timeDayRepository;
	
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
	@Transactional
	public void deleteUser(User user) throws Exception{
		this._userRepository.findAll().forEach(userTemp ->{
			this._userRepository.save(userTemp);
			userTemp.getDevelopers().forEach(underUser ->{
				if(underUser.getId()==user.getId()) {
						try {
							this.removeDeveloper(userTemp, user);
						} catch (Exception e) {
							// TODO Auto-generated catch block
						}
				}
			});
			
		});
		this._timeDayRepository.findByUserId(user.getId()).forEach(timeDayTemp ->{
			this._timeDayRepository.delete(timeDayTemp);
		});
		this._userRepository.delete(this._userRepository.findById(user.getId()).orElse(null));
		
	}

	
	@Override
	@Transactional
	public User toUser(User user) {
		User temp=_userRepository.findById(user.getId()).orElse(null);
		this._userRepository.save(temp);
		temp.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_DEV).orElse(null));
		return temp;
	}

	@Override
	@Transactional
	public User toManager(User user) {
		User temp=_userRepository.findById(user.getId()).orElse(null);
		this._userRepository.save(temp);
		temp.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_MANAGER).orElse(null));
		return temp;
	}

	@Override
	@Transactional
	public User toAdmin(User user) {
		User temp=_userRepository.findById(user.getId()).orElse(null);
		this._userRepository.save(temp);
		temp.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_ADMIN).orElse(null));
		return temp;
	}

	@Override
	public Set<User> addDeveloper(User manager, User developer) throws Exception {
		if(manager.getType().getName().equals(Constants.NAME_USER_TYPE_MANAGER) ||	manager.getType().getName().equals(Constants.NAME_USER_TYPE_ADMIN)) {//Vérifie que le manager est bien un manager ou admin
			Set<User>developers=manager.getDevelopers();
			developers.add(developer);
			manager.setDevelopers(developers);
			this._userRepository.save(manager);
		}
		else {
			throw new Exception("User with id "+ manager.getId().toString()+ " does not have the rights to add a developer.");
		}
		return manager.getDevelopers();
	}
	@Override
	@Transactional
	public Set<User> removeDeveloper(User manager, User developer) throws Exception {
		User managerEntity=this._userRepository.getById(manager.getId());
		User developerEntity=this._userRepository.getById(developer.getId());
		if(managerEntity.getType().getName().equals(Constants.NAME_USER_TYPE_MANAGER) ||	managerEntity.getType().getName().equals(Constants.NAME_USER_TYPE_ADMIN)) {//Vérifie que le manager est bien un manager ou admin
			Set<User>developers=managerEntity.getDevelopers();
			developers.remove(developerEntity);
			managerEntity.setDevelopers(developers);
			this._userRepository.save(managerEntity);
		}
		else {
			throw new Exception("User with id "+ managerEntity.getId().toString()+ " does not have the rights to remove a developer.");
		}
		return managerEntity.getDevelopers();
	}

	
}
