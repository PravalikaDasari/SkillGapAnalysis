package com.employee.service;

import java.util.Optional;

import com.employee.bean.EmployeeBean;
import com.employee.entity.EmployeeEntity;
import com.employee.exception.EmployeeNotFoundException;

public interface EmployeeService 
{
	public EmployeeBean saveEmployee(EmployeeBean bean) throws NullPointerException;
	void updateMyPassword(Long eId,String email,String password);
	Optional<EmployeeEntity> loginEmployee(String email, String password) throws EmployeeNotFoundException;
}
