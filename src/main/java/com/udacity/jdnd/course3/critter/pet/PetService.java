package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.persistence.Customer;
import com.udacity.jdnd.course3.critter.persistence.Pet;
import com.udacity.jdnd.course3.critter.persistence.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.persistence.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public PetDTO savePet(PetDTO petDTO){

		Pet pet = toPet(petDTO);
		petRepository.save(pet);

		petDTO.setId(pet.getId());

		return petDTO;
	}

	public PetDTO getPet(Long id){

		Pet pet = petRepository.findById(id).get();

		return toPetDTO(pet);
	}

	public List<PetDTO> getAllPets(){

		List<Pet> pets = petRepository.findAll();

		return pets.stream().map(this::toPetDTO).collect(Collectors.toList());
	}

	public List<PetDTO> getPetByOwner(Long ownerId){

		Customer owner = customerRepository.findById(ownerId).get();
		List<Pet> pets = owner.getPets();

		return pets.stream().map(this::toPetDTO).collect(Collectors.toList());
	}

	private PetDTO toPetDTO(Pet pet){

		PetDTO petDTO = new PetDTO();
		petDTO.setId(pet.getId());
		petDTO.setName(pet.getName());
		petDTO.setType(pet.getType());
		petDTO.setOwnerId(pet.getCustomer().getId());
		petDTO.setNotes(pet.getNotes());

		return petDTO;
	}

	private Pet toPet(PetDTO petDTO){

		Pet pet = new Pet();
		if (petDTO.getId() != 0) pet.setId(petDTO.getId());
		pet.setName(petDTO.getName());
		pet.setType(petDTO.getType());
		pet.setBirthDate(petDTO.getBirthDate());
		pet.setCustomer(customerRepository.findById(petDTO.getOwnerId()).get());
		pet.setNotes(petDTO.getNotes());

		return pet;
	}

}
