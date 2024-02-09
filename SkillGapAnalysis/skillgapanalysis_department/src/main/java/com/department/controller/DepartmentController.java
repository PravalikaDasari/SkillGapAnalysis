package com.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.bean.DepartmentBean;
import com.department.entity.DepartmentEntity;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;
import com.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping(path = "/insert")
	public ResponseEntity<DepartmentBean> inserting(@RequestBody DepartmentBean departmentBean) throws ObjectEmptyException
	{
		log.info("insertion started");
		try {
			if(departmentBean==null)
				throw new ObjectEmptyException("Object was empty ,please provide valid information");
			else {   
				departmentService.save(departmentBean);
			}
		} catch (ObjectEmptyException e) {
			throw new ObjectEmptyException("Object was empty ,please provide valid information");
		}
		ResponseEntity<DepartmentBean> responseEntity=new ResponseEntity<>(departmentBean,HttpStatus.CREATED);
		log.info("insertion ended");
		return responseEntity;
	}
	
	@DeleteMapping(path = "/delete/{departmentId}")
	public ResponseEntity<DepartmentBean> deleteById(@RequestBody @PathVariable String departmentId) throws RecordNotFoundException
	{
		log.info("deletion started");
		if(departmentId!=null)
		{
			try {
				departmentService.delete(departmentId,"InActive");
			log.info("Deletion ended");
			return new ResponseEntity<DepartmentBean>(HttpStatus.OK);
			}
			catch (RecordNotFoundException e) {
				throw new RecordNotFoundException("Recod not found with this id"+departmentId);
			}
		}
		else
		{
			throw new RecordNotFoundException("Recod not found with this id"+departmentId);
		}
	}
	@GetMapping(path = "/getById/{departmentId}")
	public ResponseEntity<DepartmentBean> getById(@RequestBody @PathVariable String departmentId) throws RecordNotFoundException
	{
		log.info("getting with id method started");
		try {
			 DepartmentBean byId = departmentService.getById(departmentId);
			 if(byId!=null)
			 {
				 departmentService.getById(departmentId);
			     log.info("Getting eith id method ended");
			     return new ResponseEntity<DepartmentBean>(byId,HttpStatus.OK);
			 }
			 else
			 {
					throw new RecordNotFoundException("Record not found with this id "+departmentId);
			 }
		} catch (RecordNotFoundException e) {
			throw new RecordNotFoundException("Record not found with this id "+departmentId);
		}
	}
	@GetMapping(path = "/getAll")
	public ResponseEntity<List<DepartmentEntity>> getAll() throws RecordNotFoundException
	{
		try {
			log.info("Entered into getAll method");
			List<DepartmentEntity> byAll = departmentService.getByAll();
			log.info("Ended into getAll method");
			return new ResponseEntity<List<DepartmentEntity>>(byAll,HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			throw new RecordNotFoundException("Records not found in this talble");
		}
	}
}
