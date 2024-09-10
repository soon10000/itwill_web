package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Job;
import com.itwill.springboot3.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller @Slf4j @RequiredArgsConstructor @RequestMapping("/job")
public class JobController {
	
	private final JobService jobSvc;
	
	@GetMapping("/list")
	public void jobList(Model model) {
		log.info("jobList()");
		
		List<Job> list = jobSvc.jobFindAll();
		model.addAttribute("jobs", list);
	}

}
