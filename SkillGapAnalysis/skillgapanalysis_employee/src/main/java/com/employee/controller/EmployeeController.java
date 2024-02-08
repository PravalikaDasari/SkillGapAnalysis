package com.employee.controller;

import java.io.IOException;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.employee.bean.EmployeeBean;
import com.employee.entity.EmployeeEntity;
import com.employee.exception.IdNotFoundException;
import com.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController 
{	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/check")
	public String check() {
		log.info("welcome to project");
		return "welcome to project";
	}
	
	//for register and insert
	@PostMapping("/insertIntoEmployee")
	public ResponseEntity<EmployeeBean> saveEmployee(@RequestBody EmployeeBean bean )
	{
		log.info("save controller start");
		try {
			EmployeeBean employee = employeeService.saveEmployee(bean);
			log.info("save controller end");
			return new ResponseEntity<EmployeeBean>(employee,HttpStatus.CREATED);
		}catch(NullPointerException exception)
		{
			throw new NullPointerException(exception.getMessage());
		}
	}
	
	//for login
	@GetMapping("/login/{email}/{password}")
	public Optional<EmployeeEntity> loginToEmployeeAccount(@PathVariable String email, @PathVariable String password) {
		return employeeService.loginEmployee(email,password);
	}
		
	//for update password
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updateMyPassword( @RequestParam Long eId, @RequestParam String email,
	        @RequestParam String password) {
		log.info("update password controller is started");
		employeeService.updateMyPassword(eId,email,password);
		log.info("update password controller is ended");
	    return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
	}
	
//	@PutMapping("/updateCredentials")
//	public ResponseEntity<EmployeeBean> updateCredentials(@RequestBody EmployeeBean bean)
//	{
//		log.info("update controller start");
//		try {
//			EmployeeBean employee = employeeService.saveEmployee(bean);
//			log.info("update controller end");
//			return new ResponseEntity<EmployeeBean>(employee,HttpStatus.CREATED);
//		}catch(NullPointerException exception)
//		{
//			throw new NullPointerException(exception.getMessage());
//		}
//	}
	
	//to fetch a single record
	@GetMapping(path = "/getByEmail/{email}")
	public ResponseEntity<EmployeeBean> getAdmin(@PathVariable String email) throws IdNotFoundException
	{
		log.info(" get by email controller is started");
		EmployeeBean bean = null;
		try {
			bean=employeeService.getByEmail(email);
		}catch(IdNotFoundException e) {
			throw new IdNotFoundException(e.getMessage());
		}
		log.info(" get by email controller is ended");
		return new ResponseEntity<>(bean,HttpStatus.FOUND);
	}
	
	//to fetch all
	@GetMapping(path="/getAll")
	public ResponseEntity<List<EmployeeBean>> getAll()
	{
		log.info("get all method started ");
		List<EmployeeBean> list = employeeService.fetchAll();
		log.info("get all method ended ");
		return new ResponseEntity<>(list,HttpStatus.FOUND) ;
	}
	
	//for deletion
	@PutMapping(path="/updateStatus/{email}/{status}")
	public ResponseEntity<String> updateEmployeeStatus(@PathVariable String email ,@PathVariable String status) throws IdNotFoundException
	{
		try {
			employeeService.updateStatus(email,status);
		}catch(IdNotFoundException e)
		{
			throw new IdNotFoundException(e.getMessage());
		}
		return new ResponseEntity<>("employee deleted successfully", HttpStatus.OK);
	}
	
	//for uploading image
	@PostMapping("/uploadImage/{email}")
	public ResponseEntity<String> uploadProfileImage(@PathVariable String email,@RequestParam("image") MultipartFile image) throws IdNotFoundException
	{
		log.info("upload image method started");
		 try {
	            if (!image.isEmpty()) 
	            {
	            	String imageName = image.getOriginalFilename();
	                byte[] profile = image.getBytes();
	                employeeService.updateProfileImage(profile,email);
	                log.info("upload image method started");
	                return ResponseEntity.ok("image uploaded successfully: " + imageName);
	            } else {
	                return ResponseEntity.badRequest().body("image is empty or not provided.");
	            }
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
	        }
		
	}

}
