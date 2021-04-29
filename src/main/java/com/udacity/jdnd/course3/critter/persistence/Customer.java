package com.udacity.jdnd.course3.critter.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "NOTES")
	private String notes;

	// bi-directional
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<Pet> pets = new ArrayList<>();

	// bi-directional
	@ManyToMany(mappedBy = "customers")
	private List<Schedule> schedules = new ArrayList<>();

	public Customer(){

	}

	public Customer(Long id, String name, String phoneNumber, String notes, List<Pet> pets) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.notes = notes;
		this.pets = pets;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
