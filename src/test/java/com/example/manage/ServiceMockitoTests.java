package com.example.manage;


import com.example.manage.exception.ResourceNotFoundException;
import com.example.manage.model.Employee;
import com.example.manage.repository.EmployeeRepository;
import com.example.manage.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= {ServiceMockitoTests.class})
public class ServiceMockitoTests {
	
	 @Mock
	 EmployeeRepository employeeRepository;
	 
	 @InjectMocks
	 EmployeeServiceImpl employeeServiceImpl;
	 
	 public List<Employee> employee;
	 
	 @Test
	  public void test_addEmployee(){
	        Employee employee= new Employee("f02","lucky","l@l.com",4000,"computer");

	        when(this.employeeRepository.save(employee)).thenReturn(employee);
	        assertEquals(employee,employeeServiceImpl.addEmployee(employee));
	    }
	 	
	   @Test
	   public void test_getEmployee() {

	        List<Employee> employees= new ArrayList<Employee>();
	        employees.add(new Employee("f02","lucky","l@l.com",4000,"computer"));
	        employees.add(new Employee("f02","lucky","l@l.com",40000,"computer"));

	        when(this.employeeRepository.findAll()).thenReturn(employees);
	        assertEquals(2,employeeServiceImpl.getEmployee().size());

	    }
	   @Test
	   public void test_getEmployeeById(){

	        List<Employee> employees= new ArrayList<Employee>();
	        employees.add(new Employee("f02","lucky","l@l.com",40000,"computer"));
	        employees.add(new Employee("f02","lucky","l@l.com",40000,"computer"));
	        String employeeId="f02";

	        when(this.employeeRepository.findAll()).thenReturn(employees);
	        try {
	            assertEquals(employeeId, employeeServiceImpl.getEmployeeById(employeeId).getEmployeeId());
	        } catch (ResourceNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	   @Test
	    public void test_updateEmployee(){
	        Employee employee= new Employee("f02","lucky","l@l.com",4000,"computer");

	        when(this.employeeRepository.save(employee)).thenReturn(employee);
	        try {
	            assertEquals(employee,employeeServiceImpl.updateEmployee(employee));
	        } catch (ResourceNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	   @Test
	   public void test_deleteEmployee_ById() throws ResourceNotFoundException{
		   Employee employee =new Employee();
		   employee.setEmployeeName("vicky");
		   employee.setEmployeeId("f01");
		   when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(Optional.of(employee));
		   employeeServiceImpl.deleteEmployeeById(employee.getEmployeeId());
		   verify(employeeRepository).deleteById(employee.getEmployeeId());
	   }
	   @Test
		void testGetEmployeeByInvalidId() throws ResourceNotFoundException {
			Employee c1= new Employee("f02","lucky","l@l.com",4000,"computer");
			when(employeeRepository.findById("2000")).thenReturn(Optional.of(c1));
			try {
				assertThat(employeeServiceImpl.getEmployeeById("2001")).as("employee with the id 2001 doesn't exist");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	   @Test
		public void testAddEmployeeAlreadyExists() throws ResourceNotFoundException {
			Employee c1= new Employee("f02","lucky","l@l.com",4000,"computer");
			when(employeeRepository.findById("f01")).thenReturn(Optional.of(c1));
		try {
			((List<Employee>) assertThat(employeeServiceImpl.addEmployee(c1)))
			.contains("Employee with the id "+c1.getEmployeeId()+" already exist");
		}catch(Exception e) {
			
		}
		}
	   @Test
		void testupdateEmployeeDoesnotExists() throws ResourceNotFoundException {
			Employee c1= new Employee("f02","lucky","l@l.com",4000,"computer");
			when(employeeRepository.findById("101")).thenReturn(Optional.of(c1));
		try {
			((List<Employee>) assertThat(employeeServiceImpl.updateEmployee(c1)))
			.contains("Employee with the id "+c1.getEmployeeId()+" doesn't exist for update");
		}catch(Exception e) {
			
		}
		}

}

