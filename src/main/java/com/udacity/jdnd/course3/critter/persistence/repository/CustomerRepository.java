package com.udacity.jdnd.course3.critter.persistence.repository;

import com.udacity.jdnd.course3.critter.persistence.Customer;
import com.udacity.jdnd.course3.critter.persistence.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByPets(Pet pet);

}
