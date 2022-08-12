package com.example.crud.jdbc.h2.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
	Long id;
	String name;
	String telephone;
	LocalDate birthDate;
	BigDecimal salary;
	
	public Person() {
		super();
	}
	
	public Person(Long id, String name, String telephone, LocalDate birthDate, BigDecimal salary) {
		super();
		this.id = id;
		this.name = name;
		this.telephone = telephone;
		this.birthDate = birthDate;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public BigDecimal getSalary() {
		return salary;
	}
	
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
}
