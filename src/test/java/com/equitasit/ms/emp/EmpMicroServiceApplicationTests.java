package com.equitasit.ms.emp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
//@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
class EmpMicroServiceApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(context);
	}

}
