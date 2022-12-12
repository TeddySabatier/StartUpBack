package fr.tse.startuppoc.project.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tse.startuppoc.project.entity.Project;
import fr.tse.startuppoc.project.repository.ProjectRepository;
import fr.tse.startuppoc.project.service.ProjectService;

@Service
public class ProjectServiceImplement implements ProjectService {

	@Autowired
	private ProjectRepository _projectRepository;

	@Override
	public List<Project> findAllProjects() {
		return this._projectRepository.findAll();
	}

	@Override
	public Project findById(Long id) {
		return this._projectRepository.findById(id).orElse(null);
	}
	
	@Override
	public Project addProject(Project project) {
		return this._projectRepository.save(project);
	}
}
