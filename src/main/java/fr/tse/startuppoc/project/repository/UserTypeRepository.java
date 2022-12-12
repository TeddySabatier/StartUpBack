package fr.tse.startuppoc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.tse.startuppoc.project.entity.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
	
}
