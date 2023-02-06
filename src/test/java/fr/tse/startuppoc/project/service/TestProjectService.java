package fr.tse.startuppoc.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class TestProjectService {

	@Autowired
	ProjectService _projectService;
	
	@Test
	public void testFindAllProject() {
		assertEquals(2, _projectService.findAllProjects().size());
	}
	
	@Test
	public void testFindById() {
		
		assertEquals("Projet 1", _projectService.findById(_projectService.findAllProjects().get(0).getId()).getName());
	}
	
	@Test
	public void TestAddProject() {
		Project projet=new Project();
		projet.setName("Test1");
		projet = _projectService.addProject(projet);
		System.out.println(projet);
		assertEquals(3, _projectService.findAllProjects().size());
		_projectService.deleteProject(projet);
	}
	
	@Test
	public void TestDeleteProject() {
		Project projet=new Project();
		projet.setName("Test2");
		projet = _projectService.addProject(projet);
		System.out.println(projet);
		_projectService.deleteProject(projet);
		assertEquals(2, _projectService.findAllProjects().size());
	}
}
