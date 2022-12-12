package fr.tse.startuppoc.project.service;

import java.util.List;

import fr.tse.startuppoc.project.entity.UserType;

public interface UserTypeService {
	public List<UserType> findAllUserType();
	public UserType findById(Long id);
}
