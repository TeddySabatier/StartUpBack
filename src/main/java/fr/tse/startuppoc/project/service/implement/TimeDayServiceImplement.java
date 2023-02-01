package fr.tse.startuppoc.project.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.tse.startuppoc.project.entity.TimeDay;
import fr.tse.startuppoc.project.repository.TimeDayRepository;
import fr.tse.startuppoc.project.service.TimeDayService;

@Service
public class TimeDayServiceImplement implements TimeDayService {

	@Autowired
	private TimeDayRepository _timeDayRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<TimeDay> findAllTimeDay() {
		return this._timeDayRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TimeDay findById(Long id) {
		return this._timeDayRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TimeDay> findByProjectId(Long id) {
		/*List<TimeDay> allTimeDay = this._timeDayRepository.findAll();
		List<TimeDay> res = new ArrayList<TimeDay>();
		
		for (TimeDay timeDay : allTimeDay) {
			if (timeDay.getProject().getId() == id) res.add(timeDay);
		}
		return res;*/
		return this._timeDayRepository.findByProjectId(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TimeDay> findByUserId(Long id) {
		/*List<TimeDay> allTimeDay = this._timeDayRepository.findAll();
		List<TimeDay> res = new ArrayList<TimeDay>();
		
		for (TimeDay timeDay : allTimeDay) {
			if (timeDay.getUser().getId() == id) res.add(timeDay);
		}
		return res;*/
		return this._timeDayRepository.findByUserId(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TimeDay> findByUserIdAndProjectId(Long userId, Long projectId) {
		/*List<TimeDay> allTimeDay = this._timeDayRepository.findAll();
		List<TimeDay> res = new ArrayList<TimeDay>();
		
		for (TimeDay timeDay : allTimeDay) {
			if (timeDay.getUser().getId() == userId && timeDay.getProject().getId() == projectId) res.add(timeDay);
		}
		return res;*/
		return this._timeDayRepository.findByUserIdAndProjectId(userId, projectId);
	}

	@Override
	@Transactional
	public TimeDay addTimeDay(TimeDay timeDay) {
		return this._timeDayRepository.save(timeDay);
	}

	@Override
	@Transactional
	public void deleteOneTimeDay(TimeDay timeDay) {
		this._timeDayRepository.delete(timeDay);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		this._timeDayRepository.deleteById(id);
	}
}
