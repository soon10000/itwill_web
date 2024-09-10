package com.itwill.springboot5.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller @RequiredArgsConstructor @Slf4j
public class HomeController {

	@GetMapping("/")
	public String home() {
		log.info("home()");
		
		return "index";
	}
	
}
