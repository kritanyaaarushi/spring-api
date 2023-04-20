package com.autapp.entity;

import com.autapp.auditing.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bunits")
public class BUnits extends Auditable<User>{
	@Id
	@GeneratedValue
	Integer id;
	String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BUnits(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public BUnits() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
