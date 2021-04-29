package com.udacity.jdnd.course3.critter.persistence.repository;

import com.udacity.jdnd.course3.critter.persistence.Customer;
import com.udacity.jdnd.course3.critter.persistence.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	List<Pet> findByOwner(Customer customer);

}
