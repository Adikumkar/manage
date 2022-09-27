package com.example.manage.controller;


import com.example.manage.exception.ResourceNotFoundException;
import com.example.manage.model.Employee;
import com.example.manage.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {


	
	@Autowired
    private EmployeeService employeeService;
	
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addfarmer(@RequestBody Employee employee) {
		log.info("Adding employee:");
		return ResponseEntity.ok().body(this.employeeService.addEmployee(employee));
	}

	
	@GetMapping("/getAllEmployee")
	public ResponseEntity < List < Employee >> getEmployee() {
		log.debug("Getting all the employee:");
		return ResponseEntity.ok().body(employeeService.getEmployee());
	}


	@GetMapping("/getEmployee/{employeeId}")
	public ResponseEntity <Employee> getEmployeeById(@PathVariable String employeeId) throws ResourceNotFoundException {
			log.debug("getting employee by id");
			return ResponseEntity.ok().body(employeeService.getEmployeeById(employeeId));		
	}
	@GetMapping("/getEmployeeSalary")
	public List<Employee> getEmployeeSalary() throws ResourceNotFoundException {
		log.debug("getting employee Salary");
		return employeeService.sortEmployeeBySalary();
	}


	@PutMapping("/employeeFarmer/{employeeId}")
	public ResponseEntity < Employee > updateEmployee(@PathVariable String employeeId, @RequestBody Employee employee) throws ResourceNotFoundException {
		log.info("updating employee starting");
		employee.setEmployeeId(employeeId);
		log.debug("Updated Employee");
		return ResponseEntity.ok().body(this.employeeService.updateEmployee(employee));
    
	}
	
	
	@DeleteMapping("/deleteEmployee/{employeeId}")
	public HttpStatus deleteFarmerById(@PathVariable String employeeId) throws ResourceNotFoundException {
		log.info("Deleting employee:");
		this.employeeService.deleteEmployeeById(employeeId);
		log.debug("Employee deleted ");
		return HttpStatus.OK;
	}
	


}




