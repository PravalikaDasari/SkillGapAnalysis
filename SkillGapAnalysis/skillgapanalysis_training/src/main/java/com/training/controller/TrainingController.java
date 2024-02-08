package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.bean.TrainingBean;
import com.training.exception.IdNotFoundException;
import com.training.exception.RecordNotFoundException;
import com.training.service.TrainingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TrainingController {

	@Autowired
	private TrainingService trainingService;

	@PostMapping(path = "/save")
	public ResponseEntity<TrainingBean> save(@RequestBody TrainingBean trainingbean) throws NullPointerException{
		try {
			log.info("save start:saving training details{}", trainingbean);
			trainingService.save(trainingbean);
			ResponseEntity<TrainingBean> responeentity = new ResponseEntity<>(trainingbean, HttpStatus.CREATED);
			log.info("save end:saved training details");
			return responeentity;
		} catch (NullPointerException e) {
			throw new NullPointerException("Object Is Empty , Provide values");
		}
	}

	@GetMapping(path = "/getbyid/{training_id}")
	public ResponseEntity<TrainingBean> getById(@PathVariable long training_id) throws IdNotFoundException {
		try {
			log.info("start getbyid method trainingid" + training_id);
			TrainingBean getbyid = trainingService.getById(training_id);
			ResponseEntity<TrainingBean> responseEntity = new ResponseEntity<>(getbyid, HttpStatus.OK);
			log.info("end getting id details");
			return responseEntity;
		} catch (IdNotFoundException e) {
			throw new IdNotFoundException("Invalid trainingid");
		}
	}
	
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<List<TrainingBean>> getAll() throws RecordNotFoundException {
		try {
			log.info("start getAll method getting all details");
			List<TrainingBean> getalldata = trainingService.getAll();
			ResponseEntity<List<TrainingBean>> responseEntity = new ResponseEntity<>(getalldata, HttpStatus.OK);
			log.info("end getting all details");
			return responseEntity;
		} catch (RecordNotFoundException e) {
			throw new RecordNotFoundException("RecordNotFound");
		}
		
	}
	
	@PutMapping(path="/updateStatus/{training_id}/{status}")
	public ResponseEntity<String> updateStatus(@PathVariable long training_id ,@PathVariable String status) throws IdNotFoundException
	{
		log.info("start:updateStatus:training_id:status");
		try {
			trainingService.updateStatus(training_id,status);
		}catch(IdNotFoundException e)
		{
			throw new IdNotFoundException(e.getMessage());
		}
		log.info("end:updated successfully");
		return new ResponseEntity<>("training deleted successfully", HttpStatus.OK);
	}

}
