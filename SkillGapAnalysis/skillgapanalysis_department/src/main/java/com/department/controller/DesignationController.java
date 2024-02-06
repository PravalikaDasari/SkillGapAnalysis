package com.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.department.bean.DesignationBean;
import com.department.exception.ObjectEmptyException;
import com.department.exception.RecordNotFoundException;
import com.department.service.DesignationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/designation")
public class DesignationController 
{
	@Autowired
	private DesignationService designationService;
	@PostMapping(path = "/insert")
	public ResponseEntity<DesignationBean> inserting(@RequestBody DesignationBean designationBean) throws ObjectEmptyException
	{
		log.info("insertion started");
		try {
			if(designationBean==null)
				throw new ObjectEmptyException("Object was empty ,please provide valid information");
			else {   
			designationService.save(designationBean);
			}
		} catch (ObjectEmptyException e) {
			throw new ObjectEmptyException("Object was empty ,please provide valid information");
		}
		ResponseEntity<DesignationBean> responseEntity=new ResponseEntity<>(designationBean,HttpStatus.CREATED);
		log.info("insertion ended");
		return responseEntity;
	}
	@GetMapping(path = "/delete/{designationId}")
	public ResponseEntity<DesignationBean> deleteById(@RequestBody @PathVariable Long designationId) throws RecordNotFoundException
	{
		log.info("deletion started");
		if(designationId!=null)
		{
			try {
			designationService.delete(designationId);
			return new ResponseEntity<DesignationBean>(HttpStatus.OK);
			}
			catch (RecordNotFoundException e) {
				throw new RecordNotFoundException("Recod not found with this id"+designationId);
				
			}
		}
		else
			
		{
			throw new RecordNotFoundException("Recod not found with this id"+designationId);
		}
		
		
		
	}
}

