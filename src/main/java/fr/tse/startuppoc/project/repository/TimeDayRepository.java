package fr.tse.startuppoc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.tse.startuppoc.project.entity.TimeDay;

public interface TimeDayRepository extends JpaRepository<TimeDay, Long> {

}
