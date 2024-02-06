package com.manager.bean;

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
public class ManagerBean {

	private long managerId;
	private String managerName;
	private String managerEmail;
	private String departmentId;
	private String managerPassword;
	private String status;

    
	
}
