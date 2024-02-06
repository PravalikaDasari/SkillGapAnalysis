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
@Table(name = "designation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DesignationEntity {
	@Id
	@Column(name = "designation_id")
	private Long designationId;
	@Column(name = "designation_name")
	private String designationName;
	@Column(name = "department_id")
	private String departmentId;
	@Column(name = "status")
	private String status;
}

