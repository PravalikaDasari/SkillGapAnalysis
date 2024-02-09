package com.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.bean.DepartmentBean;
import com.department.entity.DepartmentEntity;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;
import com.department.repository.DepartmentRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@Transactional
public class DepartmentServiceImplementation implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	public DepartmentBean convertEntityToBean(DepartmentEntity entity)
	{
		DepartmentBean bean =new DepartmentBean();
		bean.setDepartmentId(entity.getDepartmentId());
		bean.setDepartmentName(entity.getDepartmentName());
		bean.setManagerId(entity.getManagerId());
		bean.setStatus(entity.getStatus());
		return bean;
	}
	
	public  DepartmentEntity convertBeanToEntity(DepartmentBean bean)
	{
		DepartmentEntity entity=new DepartmentEntity();
		entity.setDepartmentId(bean.getDepartmentId());
		entity.setDepartmentName(bean.getDepartmentName());
		entity.setManagerId(bean.getManagerId());
		entity.setStatus(bean.getStatus());
		return entity;
	}

	@Override
	public void save(DepartmentBean departmentBean) throws ObjectEmptyException {
		try {
		if(departmentBean!=null)
		{
			DepartmentEntity beanToEntity = convertBeanToEntity(departmentBean);
			departmentRepository.save(beanToEntity);
		}
		else
		{
			throw new ObjectEmptyException("Object was empty,please provide the information");
		}
		}
		catch(ObjectEmptyException e)
		{
			throw new ObjectEmptyException("Object was empty,please provide the information");
		}
	}

	@Override
	public void delete(String departmentId, String status) throws RecordNotFoundException {
		boolean existsById = departmentRepository.existsById(departmentId);
		if(existsById)
		{
			log.info("ENtered into department delete method");
			departmentRepository.delete(departmentId,status);
			log.info("Completed the department delete method");
		}
		else
		{
			throw new RecordNotFoundException("Record not found with this id");
		}
	}

	@Override
	public DepartmentBean getById(String departmentId) throws RecordNotFoundException {
		try {
		log.info("Entered into getting method ");
		boolean existsById = departmentRepository.existsById(departmentId);
		if(existsById)
		{
			Optional<DepartmentEntity> findById = departmentRepository.findById(departmentId);
			DepartmentBean convertBean = convertEntityToBean(findById.get());
			return convertBean;
		}
		else
		{
			throw new RecordNotFoundException("Record not found with this id "+departmentId);
		}
		}
		catch(RecordNotFoundException e)
		{
			throw new RecordNotFoundException("Record not found with this id "+departmentId);
		}
	}

	@Override
	public List<DepartmentEntity> getByAll() throws RecordNotFoundException {
		List<DepartmentEntity> findAll = departmentRepository.findAll();
		if(findAll.isEmpty())
		{
			throw new RecordNotFoundException("No records found in this tables");
		}
		return findAll;
	}
}
