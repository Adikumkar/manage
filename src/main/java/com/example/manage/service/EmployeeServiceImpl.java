package com.example.manage.service;


import com.example.manage.exception.ResourceNotFoundException;
import com.example.manage.model.Employee;
import com.example.manage.repository.EmployeeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Log4j2
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
    public Employee addEmployee(Employee employee) {
		log.debug("Adding Employee");
        return employeeRepository.save(employee);
    }

	
	@Override
	public List<Employee> getEmployee() {
		log.debug("Getting employee details");
		return this.employeeRepository.findAll();
	}

	
	 @Override
	 public Employee getEmployeeById(String employeeId) throws ResourceNotFoundException {

	        Optional < Employee > employeeData = this.employeeRepository.findById(employeeId);
	        log.info("Getting employee details by id");
	        if (employeeData.isPresent()) {
	        	log.debug("Getting employee by id:");
	            return employeeData.get();
	        } else {
	        	log.debug("No content");
	            throw new ResourceNotFoundException("Record not found with id : ");
	        }
	    }

	 @Override
	 public Employee updateEmployee(Employee employee) throws ResourceNotFoundException {
	        Optional < Employee > employeeDetails = this.employeeRepository.findById(employee.getEmployeeId());
	        log.info("Updating employee");
	        if (employeeDetails.isPresent()) {
	        	Employee employeeUpdate = employeeDetails.get();
	        	employeeUpdate.setEmployeeId(employee.getEmployeeId());
	        	employeeUpdate.setEmployeeName(employee.getEmployeeName());
	        	employeeUpdate.setEmployeeEmail(employee.getEmployeeEmail());
	        	employeeRepository.save(employeeUpdate);
	        	log.debug("updated employee:");
	            return employeeUpdate;
	        } else {
	        	log.debug("no content");
	            throw new ResourceNotFoundException("Record not found with id : ");
	        }
	    }


	@Override
    public void deleteEmployeeById(String employeeId) throws ResourceNotFoundException {
        Optional < Employee > employeeDetails = this.employeeRepository.findById(employeeId);
        log.info("Deleting farmer");
        if (employeeDetails.isPresent()) {
        	 log.debug("Deleted employee");
            this.employeeRepository.deleteById(employeeId);
        } else {
        	 log.debug("No content");
            throw new ResourceNotFoundException("Record not found with id : " + employeeId);
        }

    }

	@Override
	public List<Employee> sortEmployeeBySalary() {
		List<Employee>employees=employeeRepository.findAll();

		List<Employee> employeeHavingSalaryGreaterThan1000 =employees
				.stream()
				.filter(e-> e.getSalary()> 1000)
				.collect(Collectors.toList());
		return employeeHavingSalaryGreaterThan1000;
	}

}
