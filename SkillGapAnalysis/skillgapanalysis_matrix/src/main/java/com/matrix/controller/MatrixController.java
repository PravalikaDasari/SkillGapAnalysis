package com.matrix.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matrix.bean.MatrixBean;
import com.matrix.entity.MatrixEntity;
import com.matrix.service.MatrixService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MatrixController 
{	
	@Autowired
	private MatrixService matrixService;
	
	
	
	@PostMapping("/addNewMatrix")
	public ResponseEntity<MatrixBean> saveMatrix(@RequestBody MatrixBean bean)
	{
		log.info("controller start");
		try {
			 MatrixBean matrix = matrixService.saveMatrix(bean);
			log.info("controller end");
			return new ResponseEntity<MatrixBean>(matrix,HttpStatus.CREATED);
		}catch(NullPointerException exception)
		{
			throw new NullPointerException(exception.getMessage());
		}
	}
	

		
		
//		@PutMapping("/update")
//		public ResponseEntity<String> updateMyPassword( @RequestParam Long eId, @RequestParam String email,
//		        @RequestParam String password) {
//			employeeService.updateMyPassword(eId,email,password);
//		    return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
//		}
	
	
	
}
