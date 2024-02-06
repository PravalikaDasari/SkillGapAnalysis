package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<TrainingBean> save(@RequestBody TrainingBean trainingbean) {
		try {
			log.info("save:saving training details{}", trainingbean);
			trainingService.save(trainingbean);
			ResponseEntity<TrainingBean> responeentity = new ResponseEntity<>(trainingbean, HttpStatus.CREATED);
			return responeentity;
		} catch (Exception e) {
			throw new NullPointerException("ObjectIsEmptyProvidevalues");
		}
	}

	@GetMapping(path = "/getbyid/{training_id}")
	public TrainingBean getById(@PathVariable long training_id) throws IdNotFoundException {
		try {
			log.info("trainingid" + training_id);
			TrainingBean getbyid = trainingService.getById(training_id);
			return getbyid;
		} catch (Exception e) {
			throw new IdNotFoundException("Invalid trainingid");
		}
	}
	
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<List<TrainingBean>> getAll() throws RecordNotFoundException {
		try {
			log.info("getting all details");
			List<TrainingBean> getalldata = trainingService.getAll();
			ResponseEntity<List<TrainingBean>> responseEntity = new ResponseEntity<>(getalldata, HttpStatus.OK);
			return responseEntity;
		} catch (Exception e) {
			throw new RecordNotFoundException("RecordNotFound");
		}
		
	}

}
