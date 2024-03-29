package fr.tse.startuppoc.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.tse.startuppoc.project.entity.TimeDay;
import fr.tse.startuppoc.project.service.TimeDayService;

@RestController
@CrossOrigin(origins="*",maxAge=3600,methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH, RequestMethod.DELETE})
public class TimeDayController {

	@Autowired
	private TimeDayService _timeDayService;
	
	@GetMapping("/timeday")
	List<TimeDay> getAllTimeDay() {
		return this._timeDayService.findAllTimeDay();
	}
	
	@GetMapping("/timeday/{id}")
	TimeDay getTimeDayById(@PathVariable Long id) {
		return this._timeDayService.findById(id);
	}
	
	@GetMapping("/timeday/user/{id}")
	List<TimeDay> getTimeDayByUserId(@PathVariable Long id) {
		return this._timeDayService.findByUserId(id);
	}
	
	@GetMapping("/timeday/user/{userId}/project/{projectId}")
	List<TimeDay> getTimeDayByUserIdAndProjectId(@PathVariable Long userId, @PathVariable Long projectId) {
		return this._timeDayService.findByUserIdAndProjectId(userId, projectId);
	}
	
	@GetMapping("/timeday/project/{id}")
	List<TimeDay> getTimeDayByProjectId(@PathVariable Long id) {
		return this._timeDayService.findByProjectId(id);
	}
	
	@PostMapping("/timeday")
	TimeDay postTimeDay(@Valid @RequestBody TimeDay timeDay) {
		return this._timeDayService.addTimeDay(timeDay);
	}
	
	@DeleteMapping("/timeday")
	void deleteTimeDay(@Valid @RequestBody TimeDay timeDay) {
		this._timeDayService.deleteOneTimeDay(timeDay);
	}
	
	@DeleteMapping("/timeday/{id}")
	void deleteTimeDay(@PathVariable Long id) {
		this._timeDayService.deleteById(id);
	}
}
