package com.itwill.springboot3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.springboot3.domain.Job;
import com.itwill.springboot3.repository.JobRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @Slf4j @RequiredArgsConstructor
public class JobService {
	
	private final JobRepository jobRepo;
	
	public List<Job> jobFindAll(){
		log.info("jobFindAll()");
		return jobRepo.findAll();
	}

}
