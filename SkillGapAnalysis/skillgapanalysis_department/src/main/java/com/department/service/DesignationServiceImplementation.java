package com.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.bean.DesignationBean;
import com.department.entity.DesignationEntity;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;
import com.department.repository.DesignationRepository;

@Service
public class DesignationServiceImplementation implements DesignationService{
	@Autowired
	private DesignationRepository designationRepository;
	DesignationBean designationBean=new DesignationBean();
	@Override
	public void save(DesignationBean designationBean) throws ObjectEmptyException {
		DesignationEntity designationEntity=new DesignationEntity();
		designationEntity.setDesignationId(designationBean.getDesignationId());
		designationEntity.setDesignationName(designationBean.getDesignationName());
		designationEntity.setDepartmentId(designationBean.getDepartmentId());
		designationEntity.setStatus(designationBean.getStatus());
		try {
			if(designationBean==null)
				throw new ObjectEmptyException("Object is empty please provide information");
			else
				designationRepository.save(designationEntity);
		}
		catch (ObjectEmptyException e) {
			 
			throw new ObjectEmptyException("Object is empty please provide information");

		}
		
	}
	@Override
	public void delete(Long designationId) throws RecordNotFoundException{
		//Optional<DesignationEntity> designationEntity=designationRepository.findById(designationId);
//		if(designationEntity.get().getDesignationId()!=null)
//		{
//			designationRepository.delete(designationId,status);
//		}
//		else
//		{
//			throw new RecordNotFoundException("Record not found with this id");
//		}
		DesignationEntity entity=new DesignationEntity();
		boolean existsById = designationRepository.existsById(designationId);
		if(existsById)
		{
			entity.setStatus("inactive");
			
		}
		else
		{
			throw new RecordNotFoundException("Record not found with this id");
		}
	}

}
