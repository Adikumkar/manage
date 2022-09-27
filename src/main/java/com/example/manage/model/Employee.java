package com.example.manage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "farmers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	private String employeeId;
	private String employeeName;
	private String employeeEmail;
	private long salary;
	private String empDept;
	
}