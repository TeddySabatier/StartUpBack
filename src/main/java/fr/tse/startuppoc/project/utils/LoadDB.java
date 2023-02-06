package fr.tse.startuppoc.project.utils;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import fr.tse.startuppoc.project.entity.Project;
import fr.tse.startuppoc.project.entity.TimeDay;
import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.entity.UserType;
import fr.tse.startuppoc.project.repository.ProjectRepository;
import fr.tse.startuppoc.project.repository.TimeDayRepository;
import fr.tse.startuppoc.project.repository.UserRepository;
import fr.tse.startuppoc.project.repository.UserTypeRepository;

@Configuration
public class LoadDB {

//	@Bean
//	@Profile("!test")
//	CommandLineRunner initDB(ProjectRepository _projectRepository, 
//			UserRepository _userRepository, 
//			UserTypeRepository _userTypeRepository, 
//			TimeDayRepository _timeDayRepository) {
//
//		return args -> {	
//			System.out.println("TEST");
//			//System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//			initUserTypesTest(_userTypeRepository);
//		};
//	}
	@Bean
	@Profile("!test")
	CommandLineRunner initDB(ProjectRepository _projectRepository, 
			UserRepository _userRepository, 
			UserTypeRepository _userTypeRepository, 
			TimeDayRepository _timeDayRepository) {

		return args -> {	
			System.out.println("TEST");
			//System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			if(_userTypeRepository.findAll().size()==0) {
				initUserTypesTest(_userTypeRepository);
				User admin=new User();
				admin.setFirstname("Admin");
				admin.setLastname("Admin");
				admin.setLogin("admin");
				admin.setPassword("QTvBVVOF/LG9DveJ5eZc1IMI/d2aqTQbpABcsBS5n34=");
				admin.setType(_userTypeRepository.getById(Constants.ID_USER_TYPE_ADMIN));
				_userRepository.save(admin);
			}
		};
	}
	@Bean
	@Profile("test")
	CommandLineRunner initDBTest(ProjectRepository _projectRepository, 
			UserRepository _userRepository, 
			UserTypeRepository _userTypeRepository, 
			TimeDayRepository _timeDayRepository) {

		return args -> {	
			System.out.println("TEST");
			//System.out.println(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
			initUserTypesTest(_userTypeRepository);
			initUsers(_userRepository, _userTypeRepository);
			initProjectsTest(_projectRepository);
			initTimeDayTest(_timeDayRepository);
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
		
		Project project2 = new Project();
		project2.setName("Projet 2");
		_projectRepository.save(project2);
	}
	
	static private void initTimeDayTest(TimeDayRepository _timeDayRepository) {
		Project project1 = new Project();
		project1.setId(1L);
		Project project2 = new Project();
		project2.setId(2L);
		
		User user1 = new User();
		user1.setId(1L);
		User user2 = new User();
		user2.setId(2L);
		User user3 = new User();
		user3.setId(3L);
		
		TimeDay timeDay1 = new TimeDay();
		timeDay1.setDate(Date.valueOf("2022-12-13"));
		timeDay1.setGranularity((float) 0.5);
		timeDay1.setProject(project1);
		timeDay1.setUser(user1);
		
		TimeDay timeDay2 = new TimeDay();
		timeDay2.setDate(Date.valueOf("2022-12-08"));
		timeDay2.setGranularity((float) 0.75);
		timeDay2.setProject(project2);
		timeDay2.setUser(user1);
		
		TimeDay timeDay3 = new TimeDay();
		timeDay3.setGranularity((float) 0.25);
		timeDay3.setProject(project1);
		timeDay3.setUser(user2);
		
		TimeDay timeDay4 = new TimeDay();
		timeDay4.setDate(Date.valueOf("2022-12-10"));
		timeDay4.setGranularity((float) 1);
		timeDay4.setProject(project2);
		timeDay4.setUser(user2);
		
		_timeDayRepository.save(timeDay1);
		_timeDayRepository.save(timeDay2);
		_timeDayRepository.save(timeDay3);
		_timeDayRepository.save(timeDay4);
	}
}
