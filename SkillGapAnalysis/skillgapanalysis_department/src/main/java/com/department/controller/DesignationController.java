package com.department.controller;

import java.util.List;
import java.util.Optional;

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

import com.department.bean.DesignationBean;
import com.department.entity.DesignationEntity;
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
	@DeleteMapping(path = "/delete/{designationId}")
	public ResponseEntity<DesignationBean> deleteById(@RequestBody @PathVariable Long designationId) throws RecordNotFoundException
	{
		log.info("deletion started");
		if(designationId!=null)
		{
			try {
			designationService.delete(designationId,"InActive");
			log.info("Deletion ended");
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
	@GetMapping(path = "/getById/{designationId}")
	public ResponseEntity<DesignationBean> getById(@RequestBody @PathVariable Long designationId) throws RecordNotFoundException
	{
		log.info("getting with id method started");
		try {
			 DesignationBean byId = designationService.getById(designationId);
			 if(byId!=null)
			 {
				 designationService.getById(designationId);
			     log.info("Getting eith id method ended");
			     return new ResponseEntity<DesignationBean>(byId,HttpStatus.OK);
			 }
			 else
			 {
					throw new RecordNotFoundException("Record not found with this id "+designationId);
			 }
		} catch (RecordNotFoundException e) {
			throw new RecordNotFoundException("Record not found with this id "+designationId);
		}
	}
	@GetMapping(path = "/getAll")
	public ResponseEntity<List<DesignationEntity>> getAll() throws RecordNotFoundException
	{
		try {
			log.info("Entered into getAll method");
			List<DesignationEntity> byAll = designationService.getByAll();
			log.info("Ended into getAll method");
			return new ResponseEntity<List<DesignationEntity>>(byAll,HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			throw new RecordNotFoundException("Records not found in this talble");
		}
	}
}

