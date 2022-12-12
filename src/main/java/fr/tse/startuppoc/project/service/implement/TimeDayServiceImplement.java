package fr.tse.startuppoc.project.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.tse.startuppoc.project.entity.TimeDay;
import fr.tse.startuppoc.project.repository.TimeDayRepository;
import fr.tse.startuppoc.project.service.TimeDayService;

@Service
public class TimeDayServiceImplement implements TimeDayService {

	@Autowired
	private TimeDayRepository _timeDayRepository;
	
	@Override
	public List<TimeDay> findAllTimeDay() {
		return this._timeDayRepository.findAll();
	}

	@Override
	public TimeDay findById(Long id) {
		return this._timeDayRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<TimeDay> findByUserId(Long id) {
		List<TimeDay> allTimeDay = this._timeDayRepository.findAll();
		List<TimeDay> res = new ArrayList<TimeDay>();
		
		for (TimeDay timeDay : allTimeDay) {
			if (timeDay.getUser().getId() == id) res.add(timeDay);
		}
		return res;
	}
	
	@Override
	public List<TimeDay> findByProjectId(Long id) {
		List<TimeDay> allTimeDay = this._timeDayRepository.findAll();
		List<TimeDay> res = new ArrayList<TimeDay>();
		
		for (TimeDay timeDay : allTimeDay) {
			if (timeDay.getProject().getId() == id) res.add(timeDay);
		}
		return res;
	}

	@Override
	public TimeDay addTimeDay(TimeDay timeDay) {
		return this._timeDayRepository.save(timeDay);
	}

	@Override
	public void deleteById(Long id) {
		this._timeDayRepository.deleteById(id);
	}
	
	@Override
	public void deleteOneTimeDay(TimeDay timeDay) {
		this._timeDayRepository.delete(timeDay);
	}
}
