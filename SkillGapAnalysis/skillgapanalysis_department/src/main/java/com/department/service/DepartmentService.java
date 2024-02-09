package com.department.service;

import java.util.List;

import com.department.bean.DepartmentBean;
import com.department.entity.DepartmentEntity;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;

public interface DepartmentService {
	public void save(DepartmentBean designationBean) throws ObjectEmptyException ;
	public void delete(String departmentId,String status)throws RecordNotFoundException;
	public DepartmentBean getById(String departmentId)throws RecordNotFoundException;
	public List<DepartmentEntity> getByAll()throws RecordNotFoundException;
}
