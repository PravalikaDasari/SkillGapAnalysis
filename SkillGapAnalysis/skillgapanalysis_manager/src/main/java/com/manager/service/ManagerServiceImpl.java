package com.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.manager.bean.ManagerBean;
import com.manager.entity.ManagerEntity;
import com.manager.exception.ManagerNotFound;
import com.manager.repository.ManagerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepository managerRepository;
	
	public ManagerEntity convertBeanToEntity(ManagerBean bean)
	{
		ManagerEntity entity=new ManagerEntity();
		entity.setManagerId(bean.getManagerId());
		entity.setManagerEmail(bean.getManagerEmail());
		entity.setManagerPassword(bean.getManagerPassword());
		entity.setDepartmentId(bean.getDepartmentId());
		entity.setStatus(bean.getStatus());
		entity.setFirstName(bean.getFirstName());
		entity.setLastName(bean.getLastName());
		entity.setMiddileName(bean.getMiddileName());
		entity.setImage(bean.getImage());
		return entity;
	}
	public ManagerBean convertEntityToBean(ManagerEntity entity)
	{
		ManagerBean bean =new ManagerBean();
		bean.setManagerId(entity.getManagerId());
		bean.setManagerEmail(entity.getManagerEmail());
		bean.setManagerPassword(entity.getManagerPassword());
		bean.setDepartmentId(entity.getDepartmentId());
		bean.setStatus(entity.getStatus());
		bean.setFirstName(entity.getFirstName());
		bean.setLastName(entity.getLastName());
		bean.setMiddileName(entity.getMiddileName());
		bean.setImage(entity.getImage());
		return bean;
	}
	@Override
	public ManagerBean insertManagerDetails(ManagerBean bean) {
		
		try {
			if(bean!=null)
			{
				ManagerEntity entity = convertBeanToEntity(bean);
				ManagerEntity entity2 = managerRepository.save(entity);
				ManagerBean bean2 = convertEntityToBean(entity2);
				return bean2;
			}
			else {
				throw new NullPointerException("ManagerBean is null");
			}
		}
		catch(Exception exception)
		{
			throw new NullPointerException("ManagerBean is null");
		}
	}
	@Override
	public void deleteManagerDetails(long managerId,String status) throws ManagerNotFound {

		try {
			boolean existsById = managerRepository.existsById(managerId);
			log.info("boolean value"+existsById);
            if(existsById)
            {
            	managerRepository.deleteManagerDetails(managerId,status);
            	log.info("working");
            }		
			else {
				throw new ManagerNotFound("invalid managerId");
			}
		}
		catch(Exception exception)
		{
			throw new ManagerNotFound("invalid managerId");
		}
	}
	@Override
	public ManagerBean getManagerDetailsById(long managerId) throws ManagerNotFound {
		try {
			Optional<ManagerEntity> findById = managerRepository.findById(managerId);
			ManagerBean bean = convertEntityToBean(findById.get());
			if(bean==null)
			{
				throw new ManagerNotFound("invalid managerId");
			}
			return bean;
		}
		catch (Exception e) {
			throw new ManagerNotFound("invalid managerId");

		}	
		
	}
	@Override
	public List<ManagerBean> getAllManagerDetails() throws ManagerNotFound {
		List<ManagerBean> beans=new ArrayList<>();
		try {
			List<ManagerEntity> findAll = managerRepository.findAll();
			log.info(""+findAll);
			if(findAll!=null)
			{
			  for (ManagerEntity managerEntity : findAll) {
				  ManagerBean bean = convertEntityToBean(managerEntity);
				  beans.add(bean);
			  }
			}
			else
			{
				throw new ManagerNotFound("no records found");
			}
		}
		catch (Exception e) {
			throw new ManagerNotFound("no records found");
		}
		return beans;
	}

	
	@Override
	public void updateProfileImage(byte[] profile, long managerId) throws ManagerNotFound 
	{
		log.info("updateProfileImage started in service ");
		try {
			if(managerId!=0)
			{
				ManagerBean managerDetailsById = getManagerDetailsById(managerId);				
				log.info(managerDetailsById.toString());
				managerRepository.updateManagerProfile(managerId, profile);
				
			}
		}catch(ManagerNotFound e) {
			throw new ManagerNotFound(e.getMessage());
		}
		log.info("updateProfileImage ended in service ");
		
		
	}
	
	
	@Override
	public void updateManagerPassword(String managerEmail, String password) throws ManagerNotFound {
		log.info("service impl");
		  long byManagerEmail = managerRepository.getByManagerEmail(managerEmail);
//		  managerRepository.findBy(managerEmail );
		log.info("bymail"+byManagerEmail);
		try{
				if (byManagerEmail!=0) {
					managerRepository.updateManagerPassword(managerEmail, password);
				}		
				else throw new ManagerNotFound("Wrong email id ");
			   }catch(ManagerNotFound e)
				{ 
				   throw new ManagerNotFound("Wrong Email id ");
				}

	}
	
}

