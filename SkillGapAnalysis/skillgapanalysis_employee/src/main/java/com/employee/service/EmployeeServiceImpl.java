package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.bean.EmployeeBean;
import com.employee.entity.EmployeeEntity;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.exception.IdNotFoundException;
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
		entity.setImage(bean.getImage());
		entity.setFirstName(bean.getFirstName());
		entity.setMiddleName(bean.getMiddleName());
		entity.setLastName(bean.getLastName());
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
		bean.setImage(entity.getImage());
		bean.setFirstName(entity.getFirstName());
		bean.setMiddleName(entity.getMiddleName());
		bean.setLastName(entity.getLastName());
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

//	Optional<AdminEntity> optional = adminrepository.findById(admin_id);
//	AdminEntity entity=null;
//	try {
//		entity = optional.orElseThrow(() ->
//		new IdNotFoundException("Admin not found with id-" + admin_id));
//	} catch (IdNotFoundException e) {
//		throw new IdNotFoundException(e.getMessage());
//	}
//	return entityToBean(entity);
	
	@Override
	public EmployeeBean getByEmail(String email) throws IdNotFoundException
	{
		Optional<EmployeeEntity> optional = repository.findByEmail(email);
		EmployeeEntity entity = null;
		try {
			entity = optional.orElseThrow(()->
			new IdNotFoundException("record not found with this "+email));
		}catch(IdNotFoundException e)
		{
			throw new IdNotFoundException(e.getMessage());
		}
		return convertEntityToBean(entity);
	}

	@Override
	public List<EmployeeBean> fetchAll() throws EmployeeNotFoundException 
	{
		List<EmployeeEntity> entityList = repository.findAll();
		if(entityList.size()==0)
		{
			throw new EmployeeNotFoundException("employee record not found");
		}
		List<EmployeeBean> beanlist = new ArrayList<>();
		EmployeeBean bean = new EmployeeBean();
		for(EmployeeEntity entity : entityList)
		{
			bean = convertEntityToBean(entity);
			beanlist.add(bean);
		}
		return beanlist;
	}

	@Override
	public void updateStatus(String email ,String status) throws IdNotFoundException 
	{
		log.info("delete started in service ");
		try {
			if(email!=null)
			{
				EmployeeBean employee = getByEmail(email);
				log.info(employee.toString());
				repository.updateStatus(email,status);
				
			}
		}catch(IdNotFoundException e) {
			throw new IdNotFoundException(e.getMessage());
		}
		log.info("delete ended in service ");
	}

	@Override
	public void updateProfileImage(byte[] profile, String email) throws IdNotFoundException 
	{
		log.info("updateProfileImage started in service ");
		try {
			if(email!=null)
			{
				EmployeeBean employee = getByEmail(email);
				log.info(employee.toString());
				repository.updateProfile(email,profile);
				
			}
		}catch(IdNotFoundException e) {
			throw new IdNotFoundException(e.getMessage());
		}
		log.info("updateProfileImage ended in service ");
		
		
	}
	
}
