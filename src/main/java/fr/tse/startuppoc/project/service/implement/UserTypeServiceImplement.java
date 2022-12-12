package fr.tse.startuppoc.project.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.tse.startuppoc.project.entity.UserType;
import fr.tse.startuppoc.project.repository.UserTypeRepository;
import fr.tse.startuppoc.project.service.UserTypeService;

public class UserTypeServiceImplement implements UserTypeService {

	@Autowired
	private UserTypeRepository _userTypeRepository;
	
	@Override
	public List<UserType> findAllUserType() {
		return this._userTypeRepository.findAll();
	}

	@Override
	public UserType findById(Long id) {
		return this._userTypeRepository.findById(id).orElse(null);
	}

}
