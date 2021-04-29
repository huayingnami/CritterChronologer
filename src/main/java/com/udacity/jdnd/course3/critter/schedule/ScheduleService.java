package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.persistence.Customer;
import com.udacity.jdnd.course3.critter.persistence.Employee;
import com.udacity.jdnd.course3.critter.persistence.Pet;
import com.udacity.jdnd.course3.critter.persistence.Schedule;
import com.udacity.jdnd.course3.critter.persistence.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.persistence.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.persistence.repository.PetRepository;
import com.udacity.jdnd.course3.critter.persistence.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public ScheduleDTO saveSchedule(ScheduleDTO scheduleDTO){

		Schedule schedule = toSchedule(scheduleDTO);
		scheduleRepository.save(schedule);

		scheduleDTO.setId(schedule.getId());
		return scheduleDTO;
	}

	public List<ScheduleDTO> getAllSchedules(){
		List<Schedule> schedules = scheduleRepository.findAll();
		return schedules.stream().map(this::toScheduleDTO).collect(Collectors.toList());
	}

	public List<ScheduleDTO> getScheduleForPet(Long petId){
		Pet pet = petRepository.findById(petId).get();
		List<Schedule> schedules = scheduleRepository.findByPets(pet);
		return schedules.stream().map(this::toScheduleDTO).collect(Collectors.toList());
	}

	public List<ScheduleDTO> getScheduleForEmployee(Long employeeId){
		Employee employee = employeeRepository.findById(employeeId).get();
		List<Schedule> schedules = scheduleRepository.findByEmployees(employee);
		return schedules.stream().map(this::toScheduleDTO).collect(Collectors.toList());
	}

	public List<ScheduleDTO> getScheduleForCustomer(Long customerId){
		Customer customer = customerRepository.findById(customerId).get();
		List<Schedule> schedules = scheduleRepository.findByCustomers(customer);
		return schedules.stream().map(this::toScheduleDTO).collect(Collectors.toList());
	}

	private ScheduleDTO toScheduleDTO(Schedule schedule){
		ScheduleDTO dto = new ScheduleDTO();
		dto.setId(schedule.getId());
		dto.setActivities(schedule.getActivities());
		dto.setEmployeeIds(schedule.getEmployees().stream().map(e -> e.getId()).collect(Collectors.toList()));
		dto.setPetIds(schedule.getPets().stream().map(p -> p.getId()).collect(Collectors.toList()));
		dto.setDate(schedule.getDate());

		return dto;
	}

	private Schedule toSchedule(ScheduleDTO dto){
		Schedule schedule = new Schedule();
		schedule.setDate(dto.getDate());
		schedule.setActivities(dto.getActivities());
		schedule.setEmployees(dto.getEmployeeIds().stream().map(id -> employeeRepository.findById(id).get()).collect(Collectors.toList()));
		schedule.setPets(dto.getPetIds().stream().map(id -> petRepository.findById(id).get()).collect(Collectors.toList()));
		schedule.setCustomers(dto.getPetIds().stream().map(id -> customerRepository.findByPets(petRepository.findById(id).get())).collect(Collectors.toList()));

		return schedule;
	}


}
