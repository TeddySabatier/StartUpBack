package fr.tse.startuppoc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.startuppoc.project.entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
