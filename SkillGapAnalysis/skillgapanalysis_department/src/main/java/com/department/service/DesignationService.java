package com.department.service;

import com.department.bean.DesignationBean;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;

public interface DesignationService {
	public void save(DesignationBean designationBean) throws ObjectEmptyException ;
	public void delete(Long designationId)throws RecordNotFoundException;
}
