package com.itwill.springboot3.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.service.LocationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller @Slf4j @RequestMapping("/location") @RequiredArgsConstructor
public class LocationController {

	private final LocationService locSvc;
	
	public void locList(Model model) {
		log.info("locList()");
	}
	
}
