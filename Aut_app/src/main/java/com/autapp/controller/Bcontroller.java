package com.autapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class Bcontroller {
	
	@GetMapping
	public ResponseEntity<String> mymethod(){
		return ResponseEntity.ok("helo welcome");
	}

}
