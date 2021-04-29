package com.udacity.jdnd.course3.critter.persistence.repository;

import com.udacity.jdnd.course3.critter.persistence.Customer;
import com.udacity.jdnd.course3.critter.persistence.Employee;
import com.udacity.jdnd.course3.critter.persistence.Pet;
import com.udacity.jdnd.course3.critter.persistence.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	List<Schedule> findByPets(Pet pet);

	List<Schedule> findByEmployees(Employee employee);

	List<Schedule> findByCustomers(Customer customer);

}
