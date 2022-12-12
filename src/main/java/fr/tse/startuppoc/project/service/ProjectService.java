package fr.tse.startuppoc.project.service;

import java.util.List;

import fr.tse.startuppoc.project.entity.Project;

public interface ProjectService {
	public List<Project> findAllProjects();
	public Project findById(Long id);
	public Project addProject(Project project);
	public void deleteProject(Project project);
}
