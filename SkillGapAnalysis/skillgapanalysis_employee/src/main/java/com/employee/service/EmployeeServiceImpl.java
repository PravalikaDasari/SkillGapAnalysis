package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.bean.EmployeeBean;
import com.employee.entity.EmployeeEntity;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService 
{
	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public EmployeeBean saveEmployee(EmployeeBean bean) 
	{
		log.info("service start");
		try {
			EmployeeEntity entity = convertBeanToEntity(bean);
			repository.save(entity);
			return convertEntityToBean(entity);
		}catch(NullPointerException e)
		{
			throw new NullPointerException(e.getMessage());
		}
		
	}

	public EmployeeEntity convertBeanToEntity(EmployeeBean bean)
	{
		log.info("convertion start");
		log.info("bean is :"+bean.toString());
		EmployeeEntity entity = new EmployeeEntity();
		entity.setEmpName(bean.getEmpName());
		entity.setDeptId(bean.getDeptId());
		entity.setDesgnId(bean.getDesgnId());
		entity.setManagerId(bean.getManagerId());
		entity.setGender(bean.getGender());
		entity.setEmail(bean.getEmail());
		entity.setPassword(bean.getPassword());
		entity.setStatus(bean.getStatus());
		log.info(entity.toString()+" convertion end");
		return entity;
	}
	
	public EmployeeBean convertEntityToBean(EmployeeEntity entity)
	{
		EmployeeBean bean = new EmployeeBean();
		bean.setEmpId(entity.getEmpId());
		bean.setEmpName(entity.getEmpName());
		bean.setDeptId(entity.getDeptId());
		bean.setDesgnId(entity.getDesgnId());
		bean.setManagerId(entity.getManagerId());
		bean.setGender(entity.getGender());
		bean.setEmail(entity.getEmail());
		bean.setPassword(entity.getPassword());
		bean.setStatus(entity.getStatus());
		return bean;
		
	}
	
	@Override
	public Optional<EmployeeEntity> loginEmployee(String email, String password) throws EmployeeNotFoundException {
		Optional<EmployeeEntity> findByacc = repository.findByEmail(email);
		if(findByacc.isPresent()) {
			EmployeeEntity empPass = findByacc.get();
			try{if(empPass.getPassword().equals(password)) {

				    return repository.findByEmail(email);
			}else throw new EmployeeNotFoundException("Wrong Password ");
		}catch(EmployeeNotFoundException e)
			{ 
			   System.out.println("Invalid password");
			   throw new EmployeeNotFoundException("Wrong Password ");
			}
			}else throw new EmployeeNotFoundException("Wrong Employee email id ");
	}
	
	@Override
	public void updateMyPassword(Long eId, String email, String password) {
		Optional<EmployeeEntity> employee = repository.findById(eId);
		//
				if (employee.isPresent()) {
					EmployeeEntity empPass = employee.get();
					try{
						if(empPass.getEmail().equals(email)) {
							empPass.setPassword(password);
					   
				}
						else throw new EmployeeNotFoundException("Wrong email id ");
			}catch(EmployeeNotFoundException e)
				{ 
				   throw new EmployeeNotFoundException("Wrong Email id ");
				}

				} else {
					throw new IllegalArgumentException("Employee not found  with " + eId +" Employee id");
				}
		
	}
	
}
