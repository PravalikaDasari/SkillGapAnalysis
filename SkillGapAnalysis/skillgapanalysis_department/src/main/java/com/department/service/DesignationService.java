package com.department.service;

import java.util.List;

import com.department.bean.DesignationBean;
import com.department.entity.DesignationEntity;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;

public interface DesignationService {
	public void save(DesignationBean designationBean) throws ObjectEmptyException ;
	public void delete(Long designationId,String status)throws RecordNotFoundException;
	public DesignationBean getById(Long designationId) throws RecordNotFoundException;
	public List<DesignationEntity> getByAll()throws RecordNotFoundException;
	
}
