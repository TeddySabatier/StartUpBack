package fr.tse.startuppoc.project.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
class TestTimeDayRepository {

	@Autowired
	private TimeDayRepository _timeDayRepository;
	
	private static Long addedTimeDayId;
	
	@Test
	@Order(1) // First test to be executed
	void findAllTest() {
		List<TimeDay> allTimeDay = this._timeDayRepository.findAll();
		assertEquals(4, allTimeDay.size());
	}
	
	@Test
	@Order(2)
	void findByIdTest() {
		TimeDay timeDay1 = this._timeDayRepository.findAll().get(0);
		TimeDay timeDay2 = this._timeDayRepository.findById(1L).orElse(null);
		assertNotNull(timeDay2);
		assertEquals(timeDay1.getDate(), timeDay2.getDate());
		assertEquals(timeDay1.getGranularity(), timeDay2.getGranularity());
	}
	
	@Test
	@Order(3)
	void saveTest() {
		int initialSize = this._timeDayRepository.findAll().size();
		TimeDay timeDay = new TimeDay();
		TimeDay addedTimeDay = this._timeDayRepository.save(timeDay);
		addedTimeDayId = addedTimeDay.getId();
		
		int finalSize = this._timeDayRepository.findAll().size();
		assertEquals(finalSize, initialSize + 1);
	}
	
	@Test
	@Order(4)
	void deleteTest() {
		int initialSize = this._timeDayRepository.findAll().size();
		TimeDay timeDay = this._timeDayRepository.findById(addedTimeDayId).orElse(null);
		assertNotNull(timeDay);
		this._timeDayRepository.delete(timeDay);
		
		int finalSize = this._timeDayRepository.findAll().size();
		assertEquals(finalSize, initialSize - 1);
	}
}
