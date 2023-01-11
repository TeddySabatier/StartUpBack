package fr.tse.startuppoc.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.startuppoc.project.entity.TimeDay;

public interface TimeDayRepository extends JpaRepository<TimeDay, Long> {
	public List<TimeDay> findByProjectId(Long id);
	public List<TimeDay> findByUserId(Long id);
	public List<TimeDay> findByUserIdAndProjectId(Long userId, Long projectId);
}
