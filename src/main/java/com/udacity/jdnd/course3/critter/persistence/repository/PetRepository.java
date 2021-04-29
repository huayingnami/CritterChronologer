package com.udacity.jdnd.course3.critter.persistence.repository;

import com.udacity.jdnd.course3.critter.persistence.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
