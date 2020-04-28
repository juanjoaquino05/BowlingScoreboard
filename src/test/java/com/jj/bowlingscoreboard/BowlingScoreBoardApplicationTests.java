package com.jj.bowlingscoreboard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest

@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
class BowlingScoreBoardApplicationTests {

	@Test
	void contextLoads() {
	}

}
