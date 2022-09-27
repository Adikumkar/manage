package com.example.manage.service;



import com.example.manage.exception.ResourceNotFoundException;
import com.example.manage.model.Employee;

import java.util.List;


public interface EmployeeService {

	 Employee addEmployee(Employee employee);
	 List<Employee> getEmployee();
	 Employee getEmployeeById(String employeeId) throws ResourceNotFoundException;
	 Employee updateEmployee(Employee employee) throws ResourceNotFoundException;
	 void deleteEmployeeById(String employeeId) throws ResourceNotFoundException;
	 List<Employee> sortEmployeeBySalary();
}
