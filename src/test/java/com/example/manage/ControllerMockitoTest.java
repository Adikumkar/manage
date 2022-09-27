package com.example.manage;


import com.example.manage.controller.EmployeeController;
import com.example.manage.exception.ResourceNotFoundException;
import com.example.manage.model.Employee;
import com.example.manage.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@SpringBootTest(classes= {ControllerMockitoTest.class})
public class ControllerMockitoTest {
	@Mock
	EmployeeServiceImpl employeeService;
	
	@InjectMocks
	EmployeeController employeeController;
	
	List<Employee> myEmployee;
	Employee employee;
	
	@Test
	@Order(1)
	public void test_getAllEmployees() {
		myEmployee=new ArrayList<Employee>();
		myEmployee.add(new Employee("f02","lucky","l@l.com",4000,"computer"));
		myEmployee.add(new Employee("f03","walky","w@w.com",4000,"mech"));
		
		when(employeeService.getEmployee()).thenReturn(myEmployee);
		ResponseEntity<List<Employee>> res =employeeController.getEmployee();
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(2,res.getBody().size());
	}
	@Test
	@Order(2)
	public void test_getEmployeeById() {
		employee= new Employee("f02","lucky","l@l.com",4000,"computer");
		String id="f02";
		
		try {
			when(employeeService.getEmployeeById(id)).thenReturn(employee);
		} catch (ResourceNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResponseEntity<Employee> res = null;
		try {
			res = employeeController.getEmployeeById(id);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(id,res.getBody().getEmployeeId());
	}
	@Test
	@Order(3)
	public void test_addEmployee() {
		
		employee= new Employee("f02","lucky","l@l.com",4000,"computer");
		
		when(employeeService.addEmployee(employee)).thenReturn(employee);
		ResponseEntity<Employee> res=employeeController.addfarmer(employee);
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(employee,res.getBody());
		
	}
	@Test
	@Order(4)
	public void test_updateEmployee() {
		employee= new Employee("f02","lucky","l@l.com",4000,"computer");
		String id="f02";
		
		try {
			when(employeeService.getEmployeeById(id)).thenReturn(employee);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		try {
			when(employeeService.updateEmployee(employee)).thenReturn(employee);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		try {
			ResponseEntity<Employee> res=employeeController.updateEmployee(id, employee);
			assertEquals(HttpStatus.OK,res.getStatusCode());
			assertEquals("f02",res.getBody().getEmployeeId());
			assertEquals("lucky",res.getBody().getEmployeeName());
			assertEquals("l@l.com",res.getBody().getEmployeeEmail());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}

	}

}
