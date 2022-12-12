package fr.tse.startuppoc.project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.startuppoc.project.entity.Project;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
public class TestProjectRepository {
	@Autowired
	private ProjectRepository _projectRepository;
	
	@Test
	void testFindAllProjects(){
		List<Project> projects = this._projectRepository.findAll();
		assertEquals(2, projects.size());	
	}
	
	@Test
	void testFindById(){
		List<Project> projects = this._projectRepository.findAll();
		Project project = this._projectRepository.findById(projects.get(0).getId()).orElse(null);
		
		assertEquals(project.getName(), projects.get(0).getName());		
	}
	
	@Test
	void testAddProject() {
		/*Project project = new Project();
		project.setName("Test");
		this._projectRepository.save(project);
		assertEquals(2,this._projectRepository.findAll().size());*/
	}
	
}
