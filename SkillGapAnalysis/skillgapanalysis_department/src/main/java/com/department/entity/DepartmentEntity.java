package com.department.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentEntity {
	@Id
	@Column(name = "department_id")
	private String departmentId;
	@Column(name = "department_name")
	private String departmentName;
	@Column(name = "manager_id")
	private Long managerId;
	@Column(name = "status")
	private String status;
}
