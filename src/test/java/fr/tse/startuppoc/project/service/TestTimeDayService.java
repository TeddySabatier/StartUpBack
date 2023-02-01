package fr.tse.startuppoc.project.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import fr.tse.startuppoc.project.entity.TimeDay;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@TestMethodOrder(OrderAnnotation.class) // Define the order in which tests are executed
public class TestTimeDayService {
	
	@Autowired
	private TimeDayService _timeDayService;
	
	private static Long addedTimeDayId;

	@Test
	@Order(1) // First test to be executed
	public void findAllTimeDayTest() {
		assertEquals(4, this._timeDayService.findAllTimeDay().size());
	}

	@Test
	@Order(2)
	public void findByIdTest() {
		TimeDay timeDay1 = this._timeDayService.findAllTimeDay().get(0);
		TimeDay timeDay2 = this._timeDayService.findById(1L);
		assertEquals(timeDay1.getDate(), timeDay2.getDate());
		assertEquals(timeDay1.getGranularity(), timeDay2.getGranularity());
	}
	
	@Test
	@Order(3)
	public void findByUserIdTest() {
		List<TimeDay> timeDay4UserId1 = this._timeDayService.findByUserId(1L);
		assertEquals(2, timeDay4UserId1.size());
	}
	
	@Test
	@Order(4)
	public void findByProjectIdTest() {
		List<TimeDay> timeDay4ProjectId1 = this._timeDayService.findByProjectId(1L);
		assertEquals(2, timeDay4ProjectId1.size());
	}
	
	@Test
	@Order(5)
	public void findByUserIdAndProjectIdTest() {
		List<TimeDay> timeDay4UserId1AndProjectId1 = this._timeDayService.findByUserIdAndProjectId(1L, 1L);
		assertEquals(1, timeDay4UserId1AndProjectId1.size());
	}
	
	@Test
	@Order(6)
	public void addTimeDayTest() {
		int initialSize = this._timeDayService.findAllTimeDay().size();
		TimeDay newTimeDay = new TimeDay();
		TimeDay addedTimeDay = this._timeDayService.addTimeDay(newTimeDay);
		addedTimeDayId = addedTimeDay.getId();
		
		int finalSize = this._timeDayService.findAllTimeDay().size();
		assertEquals(finalSize, initialSize + 1);
	}
	
	@Test
	@Order(7)
	public void deleteOneTimeDayTest() {
		int initialSize = this._timeDayService.findAllTimeDay().size();
		TimeDay timeDay = this._timeDayService.findById(addedTimeDayId);
		assertNotNull(timeDay);
		this._timeDayService.deleteOneTimeDay(timeDay);
		
		int finalSize = this._timeDayService.findAllTimeDay().size();
		assertEquals(finalSize, initialSize - 1);
	}
}
