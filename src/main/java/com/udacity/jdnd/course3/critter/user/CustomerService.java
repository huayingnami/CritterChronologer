package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.persistence.Customer;
import com.udacity.jdnd.course3.critter.persistence.Pet;
import com.udacity.jdnd.course3.critter.persistence.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.persistence.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PetRepository petRepository;

	public CustomerDTO saveCustomer(CustomerDTO customerDTO){
		Customer customer = toCustomer(customerDTO);
		customerRepository.save(customer);
		return toCustomerDTO(customer);
	}

	public List<CustomerDTO> getAllCustomers(){
		List<Customer> customers = customerRepository.findAll();
		return customers.stream().map(this::toCustomerDTO).collect(Collectors.toList());
	}

	public CustomerDTO getOwnerByPet(Long petId){
		Pet pet = petRepository.findById(petId).get();
		Customer customer = customerRepository.findByPets(pet);
		return toCustomerDTO(customer);
	}

	private CustomerDTO toCustomerDTO(Customer customer){
		CustomerDTO dto = new CustomerDTO();
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setPhoneNumber(customer.getPhoneNumber());
		if (customer.getPets() != null) dto.setPetIds(customer.getPets().stream().map(p -> p.getId()).collect(Collectors.toList()));
		dto.setNotes(customer.getNotes());

		return dto;
	}

	private Customer toCustomer(CustomerDTO dto){
		Customer customer = new Customer();
		if (dto.getId() != 0) customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setPhoneNumber(dto.getPhoneNumber());
		if (dto.getPetIds() != null) customer.setPets(dto.getPetIds().stream().map(id -> petRepository.findById(id).get()).collect(Collectors.toList()));
		customer.setNotes(dto.getNotes());

		return customer;
	}

}
