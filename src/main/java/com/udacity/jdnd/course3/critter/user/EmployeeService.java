package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.persistence.Employee;
import com.udacity.jdnd.course3.critter.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
		Employee employee = toEmployee(employeeDTO);
		employeeRepository.save(employee);
		return toEmployeeDTO(employee);
	}

	public EmployeeDTO getEmployee(Long id){
		Employee employee = employeeRepository.findById(id).get();
		return toEmployeeDTO(employee);
	}

	public void setAvailability(Set<DayOfWeek> availability, Long id){
		Employee employee = employeeRepository.findById(id).get();
		employee.setDaysAvailable(availability);
		employeeRepository.save(employee);
	}

	// employee skills must match all required skills
	public List<EmployeeDTO> findEmployeeForService(EmployeeRequestDTO employeeRequestDTO){
		Set<EmployeeSkill> skillSet = employeeRequestDTO.getSkills();
		LocalDate date = employeeRequestDTO.getDate();
		List<Employee> employees = new ArrayList<>();

			List<Employee> found = employeeRepository.findByDaysAvailable(DayOfWeek.from(date));
			employees.addAll(found.stream().filter(e -> e.getSkills().containsAll(skillSet)).collect(Collectors.toList()));

		return employees.stream().map(this::toEmployeeDTO).collect(Collectors.toList());
	}

	private Employee toEmployee(EmployeeDTO dto){
		Employee employee = new Employee();
		if (dto.getId() != 0) employee.setId(dto.getId());
		employee.setName(dto.getName());
		employee.setSkills(dto.getSkills());
		employee.setDaysAvailable(dto.getDaysAvailable());

		return employee;
	}

	private EmployeeDTO toEmployeeDTO(Employee employee){
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employee.getId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setSkills(employee.getSkills());
		employeeDTO.setDaysAvailable(employee.getDaysAvailable());

		return employeeDTO;
	}

}
