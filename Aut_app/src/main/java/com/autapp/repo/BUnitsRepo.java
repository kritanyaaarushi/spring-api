package com.autapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autapp.entity.BUnits;

@Repository
public interface BUnitsRepo extends JpaRepository<BUnits,Integer> {

	
}
