package com.example.manage.repository;


import com.example.manage.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface EmployeeRepository extends MongoRepository<Employee, String> {

}
