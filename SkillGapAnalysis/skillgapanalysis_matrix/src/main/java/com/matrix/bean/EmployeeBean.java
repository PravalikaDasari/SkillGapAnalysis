package com.matrix.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployeeBean
{
	private Long empId;
	private String empName;
	private String desgnId;
	private String deptId;
	private Long managerId;
	private String gender;
	private String email;
	private String password;
	private String status;

	
}
