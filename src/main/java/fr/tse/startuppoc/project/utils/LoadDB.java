package fr.tse.startuppoc.project.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.tse.startuppoc.project.entity.Project;
import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.entity.UserType;
import fr.tse.startuppoc.project.repository.ProjectRepository;
import fr.tse.startuppoc.project.repository.UserRepository;
import fr.tse.startuppoc.project.repository.UserTypeRepository;

@Configuration
public class LoadDB {

	@Bean
	@Profile("test")
	CommandLineRunner initDBTest(ProjectRepository _projectRepository, 
			UserRepository _userRepository, 
			UserTypeRepository _userTypeRepository) {

		return args -> {	
			System.out.println("TEST");
			//System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			initUserTypesTest(_userTypeRepository);
			initUsers(_userRepository, _userTypeRepository);
			initProjectsTest(_projectRepository);
		};
	}
	
	static private void initUserTypesTest(UserTypeRepository _userTypeRepository) {
		UserType dev = new UserType(Constants.ID_USER_TYPE_DEV, Constants.NAME_USER_TYPE_DEV);
		_userTypeRepository.save(dev);
		
		UserType manager = new UserType(Constants.ID_USER_TYPE_MANAGER, Constants.NAME_USER_TYPE_MANAGER);
		_userTypeRepository.save(manager);
		
		UserType admin = new UserType(Constants.ID_USER_TYPE_ADMIN, Constants.NAME_USER_TYPE_ADMIN);
		_userTypeRepository.save(admin);
	}
	
	static private void initUsers(UserRepository _userRepository, UserTypeRepository _userTypeRepository) {
		User dev = new User();
		dev.setFirstname("Jean");
		dev.setLastname("Dev");
		dev.setLogin("jean.dev");
		dev.setPassword("password");
		dev.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_DEV).orElse(null));
		_userRepository.save(dev);
		
		User manager = new User();
		manager.setFirstname("Jean");
		manager.setLastname("Manager");
		manager.setLogin("jean.manager");
		manager.setPassword("password");
		manager.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_MANAGER).orElse(null));
		_userRepository.save(manager);
		
		User admin = new User();
		admin.setFirstname("Jean");
		admin.setLastname("Admin");
		admin.setLogin("jean.admin");
		admin.setPassword("password");
		admin.setType(_userTypeRepository.findById(Constants.ID_USER_TYPE_ADMIN).orElse(null));
		_userRepository.save(admin);
	}

	static private void initProjectsTest(ProjectRepository _projectRepository) {
		Project project1 = new Project();
		project1.setName("Projet 1");
		
		_projectRepository.save(project1);
	}
}
