package com.employee.controller;

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

import com.employee.bean.EmployeeBean;
import com.employee.entity.EmployeeEntity;
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
	
	@PostMapping("/insertIntoEmployee")
	public ResponseEntity<EmployeeBean> saveEmployee(@RequestBody EmployeeBean bean)
	{
		log.info("controller start");
		try {
			EmployeeBean employee = employeeService.saveEmployee(bean);
			log.info("controller end");
			return new ResponseEntity<EmployeeBean>(employee,HttpStatus.CREATED);
		}catch(NullPointerException exception)
		{
			throw new NullPointerException(exception.getMessage());
		}
	}
	
	 @GetMapping("/login/{email}/{password}")
		public Optional<EmployeeEntity> loginToEmployeeAccount(@PathVariable String email, @PathVariable String password) {
			return employeeService.loginEmployee(email,password);
		}
		
		
		@PutMapping("/update")
		public ResponseEntity<String> updateMyPassword( @RequestParam Long eId, @RequestParam String email,
		        @RequestParam String password) {
			employeeService.updateMyPassword(eId,email,password);
		    return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
		}
	
	
	
}
