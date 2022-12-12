package fr.tse.startuppoc.project.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
class TestTimeDayService {
	
	@Autowired
	private TimeDayService _timeDayService;

	@Test
	void findAllTimeDayTest() {
		fail("Not yet implemented");
	}

}
