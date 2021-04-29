package com.udacity.jdnd.course3.critter.persistence.repository;

import com.udacity.jdnd.course3.critter.persistence.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findByDaysAvailable(DayOfWeek day);

}
