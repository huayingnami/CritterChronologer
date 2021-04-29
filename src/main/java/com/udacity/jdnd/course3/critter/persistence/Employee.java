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
import javax.persistence.Table;
import java.time.DayOfWeek;
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
	private Set<EmployeeSkill> skills;

	@ElementCollection
	@Enumerated(EnumType.STRING)
	@Column(name = "DAY_AVAILABLE")
	private Set<DayOfWeek> daysAvailable;

}
