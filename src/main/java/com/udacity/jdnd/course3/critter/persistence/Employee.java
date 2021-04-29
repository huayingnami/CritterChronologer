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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name = "SKILL")
	private Set<EmployeeSkill> skills = new HashSet<>();

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name = "DAY_AVAILABLE")
	private Set<DayOfWeek> daysAvailable = new HashSet<>();

	// bi-directional
	@ManyToMany(mappedBy = "employees")
	private List<Schedule> schedules = new ArrayList<>();

	public Employee() {

	}

	public Employee(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
		this.id = id;
		this.name = name;
		this.skills = skills;
		this.daysAvailable = daysAvailable;
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

	public Set<EmployeeSkill> getSkills() {
		return skills;
	}

	public void setSkills(Set<EmployeeSkill> skills) {
		this.skills = skills;
	}

	public Set<DayOfWeek> getDaysAvailable() {
		return daysAvailable;
	}

	public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
		this.daysAvailable = daysAvailable;
	}

}
