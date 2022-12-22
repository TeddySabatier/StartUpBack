package fr.tse.startuppoc.project.service;

import java.util.List;
import fr.tse.startuppoc.project.entity.TimeDay;

public interface TimeDayService {
	public List<TimeDay> findAllTimeDay();
	public TimeDay findById(Long id);
	
	public List<TimeDay> findByUserId(Long id);
	public List<TimeDay> findByProjectId(Long id);
	
	public TimeDay addTimeDay(TimeDay timeDay);
	public void deleteOneTimeDay(TimeDay timeDay);
}
