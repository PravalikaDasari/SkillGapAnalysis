package com.employee.service;

import java.util.List;
import java.util.Optional;

import com.employee.bean.EmployeeBean;
import com.employee.entity.EmployeeEntity;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.exception.IdNotFoundException;

public interface EmployeeService 
{
	public EmployeeBean saveEmployee(EmployeeBean bean) throws NullPointerException;
	void updateMyPassword(Long eId,String email,String password);
	Optional<EmployeeEntity> loginEmployee(String email, String password) throws EmployeeNotFoundException;
	public EmployeeBean getByEmail(String email) throws IdNotFoundException;
	public List<EmployeeBean> fetchAll() throws EmployeeNotFoundException;
	public void updateStatus(String email ,String status) throws IdNotFoundException;
	public void updateProfileImage(byte[] profile, String email) throws IdNotFoundException;
}
