package com.itwill.springboot3.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.springboot3.domain.Job;

import lombok.extern.slf4j.Slf4j;

@Slf4j @SpringBootTest
public class JobRepositotyTest {
	
	@Autowired
	private JobRepository jobRepo;
	
	@Test
	public void testJobFindAll() {
		
		List<Job> job = jobRepo.findAll();
		
		assertThat(job.size()).isEqualTo(19);
	}

}
