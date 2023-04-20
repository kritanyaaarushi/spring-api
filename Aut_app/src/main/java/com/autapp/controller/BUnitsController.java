package com.autapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autapp.entity.BUnits;
import com.autapp.service.BUnitsService;

@RestController
@RequestMapping("/bunits")
public class BUnitsController {
	
	@Autowired
	BUnitsService bService;
	
	@PostMapping("/save")
	public ResponseEntity<BUnits> createData(@RequestBody BUnits bunits){
		BUnits buu=bService.createData(bunits);
		return new ResponseEntity<BUnits>(buu,HttpStatus.CREATED);
	}

}
