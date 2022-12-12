package fr.tse.startuppoc.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.tse.startuppoc.project.entity.Project;
import fr.tse.startuppoc.project.entity.User;
import fr.tse.startuppoc.project.service.ProjectService;

@RestController
@CrossOrigin(origins="*",maxAge=3600,methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH})
public class ProjectController {

	@Autowired
	private ProjectService _projectService;
	
	@GetMapping("/projects")
	List<Project> getAllProjects(){
		return this._projectService.findAllProjects();
	}
	
	@GetMapping("/projects/{id}")
	Project getProjectById(@PathVariable Long id) {
		return this._projectService.findById(id);
	}
	
	@PostMapping("/project")
	Project addProject(@Valid @RequestBody Project project) {
		return this._projectService.addProject(project);		
	}
}
