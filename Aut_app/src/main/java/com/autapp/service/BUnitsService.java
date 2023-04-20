package com.autapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autapp.entity.BUnits;
import com.autapp.repo.BUnitsRepo;

@Service
public class BUnitsService {
	
	@Autowired
	BUnitsRepo bunitsRepo;
	
	public BUnits createData(BUnits bunits) {
		BUnits bu=bunitsRepo.save(bunits);
		return bu;
	}

}
