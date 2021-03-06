package com.udacity.jdnd.course3.critter.persistence;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "SCHEDULE")
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	// bi-directional
	@ManyToMany
	@JoinColumn(name = "EMPLOYEE_ID")
	private List<Employee> employees = new ArrayList<>();

	// bi-directional
	@ManyToMany
	@JoinColumn(name = "PET_ID")
	private List<Pet> pets = new ArrayList<>();

	// bi-directional
	@ManyToMany
	@JoinColumn(name = "CUSTOMER_ID")
	private List<Customer> customers = new ArrayList<>();

	@Column(name = "DATE")
	private LocalDate date;

	@ElementCollection
	@Column(name = "ACTIVITY")
	@Enumerated(EnumType.STRING)
	private Set<EmployeeSkill> activities = new HashSet<>();

	public Schedule(){

	}

	public Schedule(Long id, List<Employee> employees, List<Pet> pets, List<Customer> customers, LocalDate date, Set<EmployeeSkill> activities) {
		this.id = id;
		this.employees = employees;
		this.pets = pets;
		this.customers = customers;
		this.date = date;
		this.activities = activities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Set<EmployeeSkill> getActivities() {
		return activities;
	}

	public void setActivities(Set<EmployeeSkill> activities) {
		this.activities = activities;
	}

}
