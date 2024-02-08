package com.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private Long empId;
	
	@Column(name="image")
	private byte[] image;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="middlename")
	private String middleName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="designation_id")
	private String desgnId;
	
	@Column(name="department_id")
	private String deptId;
	
	@Column(name="manager_id")
	private Long managerId;
	
	@Column(name="employee_gender")
	private String gender;
	
	@Column(name="employee_email")
	private String email;
	
	@Column(name="employee_password")
	private String password;
	
	@Column(name="status")
	private String status;

	
}
