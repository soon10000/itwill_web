package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j @SpringBootTest
public class LocationRepositoryTest {
	
	@Autowired
	private LocationRepository locRepo;
	
	@Test
	public void testLocRepo() {
		assertThat(locRepo).isNotNull();
	}

}
