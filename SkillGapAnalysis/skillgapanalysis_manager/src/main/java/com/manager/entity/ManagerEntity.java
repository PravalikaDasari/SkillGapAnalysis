package com.manager.entity;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="manager")
public class ManagerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="manager_id")
	private long managerId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middile_name")
	private String middileName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="manager_email")
	private String managerEmail;
	
	@Column(name="department_id")
	private String departmentId;
	
	@Column(name="manager_password")
	private String managerPassword;
	
	@Column(name="status")
	private String status;
	
	@Column(name="image")
	private byte[] image;


}