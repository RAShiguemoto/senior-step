package com.seniorstep.planner;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Disabled because it requires database infrastructure not configured in this context")
class PlannerApplicationTests {

	@Test
	void contextLoads() {
	}

}
