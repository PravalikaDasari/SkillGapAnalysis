package com.department.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.bean.DesignationBean;
import com.department.entity.DesignationEntity;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;
import com.department.repository.DesignationRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Transactional
@Service
public class DesignationServiceImplementation implements DesignationService{
	@Autowired
	private DesignationRepository designationRepository;
	DesignationBean designationBean=new DesignationBean();
	public DesignationBean convertEntityToBean(DesignationEntity entity)
	{
		DesignationBean bean =new DesignationBean();
		bean.setDesignationId(entity.getDesignationId());
		bean.setDesignationName(entity.getDesignationName());
		bean.setDepartmentId(entity.getDepartmentId());
		bean.setStatus(entity.getStatus());
		return bean;
	}
	public DesignationEntity convertBeanToEntity(DesignationBean bean)
	{
		DesignationEntity entity=new DesignationEntity();
		entity.setDesignationId(bean.getDesignationId());
		entity.setDesignationName(bean.getDesignationName());
		entity.setDepartmentId(bean.getDepartmentId());
		entity.setStatus(bean.getStatus());
		
		return entity;
	}
	@Override
	public void save(DesignationBean designationBean) throws ObjectEmptyException {
//		DesignationEntity designationEntity=new DesignationEntity();
//		designationEntity.setDesignationId(designationBean.getDesignationId());
//		designationEntity.setDesignationName(designationBean.getDesignationName());
//		designationEntity.setDepartmentId(designationBean.getDepartmentId());
//		designationEntity.setStatus(designationBean.getStatus());
		try {
			if(designationBean!=null) {
				DesignationEntity designationEntity=convertBeanToEntity(designationBean);
				designationRepository.save(designationEntity);
			}
			else
			{
				throw new ObjectEmptyException("Object is empty please provide information");
			}
		}
		catch (ObjectEmptyException e) {
			 
			throw new ObjectEmptyException("Object is empty please provide information");

		}
		
	}
	@Override
	public void delete(Long designationId,String status) throws RecordNotFoundException{
		//Optional<DesignationEntity> designationEntity=designationRepository.findById(designationId);
//		if(designationEntity.get().getDesignationId()!=null)
//		{
//			designationRepository.delete(designationId,status);
//		}
//		else
//		{
//			throw new RecordNotFoundException("Record not found with this id");
//		}
		
		
		//DesignationEntity entity=new DesignationEntity();
		log.info("entered into delete method");
		boolean existsById = designationRepository.existsById(designationId);
		if(existsById)
		{
			log.info("service .....");
			designationRepository.delete(designationId,status);
			log.info("delete method ended");
			
		}
		else
		{
			throw new RecordNotFoundException("Record not found with this id");
		}
		log.info("ended in delete method");
	}
	@Override
	public DesignationBean getById(Long designationId) throws RecordNotFoundException {
		try {
		log.info("entered into getById method");
		boolean findById = designationRepository.existsById(designationId);
		if(findById)
		{
			Optional<DesignationEntity> findById2 = designationRepository.findById(designationId);
			DesignationBean bean = convertEntityToBean(findById2.get());
			return bean;
		}
		else
		{
			throw new RecordNotFoundException("Record not found with this id "+designationId);
		}
		}
		catch(RecordNotFoundException e)
		{
			throw new RecordNotFoundException("Record not found with this id "+designationId);
		}
	}
	@Override
	public List<DesignationEntity> getByAll() throws RecordNotFoundException {
		List<DesignationEntity> findAll = designationRepository.findAll();
		if(findAll.isEmpty())
		{
			throw new RecordNotFoundException("No records found in this tables");
		}
		return findAll;
	}
	
}
