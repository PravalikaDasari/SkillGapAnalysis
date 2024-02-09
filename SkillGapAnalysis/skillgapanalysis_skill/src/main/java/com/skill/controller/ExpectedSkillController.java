package com.skill.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skill.bean.ExpectedSkillBean;
import com.skill.exception.ExpectedSkillIdNotFound;
import com.skill.service.ExpectedSkillService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/expectedSkill")
public class ExpectedSkillController {

	@Autowired
	ExpectedSkillService expectedSkillService;
	
	//------------inserting Expecting Skills-----------
	@PostMapping(path = "/insertExpectedSkill")
	public ResponseEntity<ExpectedSkillBean> insertExpectedSkill(@RequestBody ExpectedSkillBean bean)
	{
		try {
			 log.info("Start::insertExpectedSkill");
			 ExpectedSkillBean bean2 = expectedSkillService.insertExpectedSkill(bean);
			 ResponseEntity<ExpectedSkillBean> responseEntity = new ResponseEntity<>(bean2, HttpStatus.CREATED);
			 log.info("End::insertExpectedSkill");

			return responseEntity;
		}
		catch (Exception e) {
			throw new NullPointerException("ExpectedSkillBean is null");
		}
		
	}
	
	//----------------deleting ExpectedSkills(soft deletion)----------------
	@GetMapping(path = "/deleteExpectedSkill/{expectedSkillId}")
	public ResponseEntity<String> deleteExpectedSkill(@PathVariable long expectedSkillId) throws ExpectedSkillIdNotFound
	{
		try {
			 log.info("Start::deleteExpectedSkill");
			 expectedSkillService.deleteExpectedSkill(expectedSkillId,"Inactive");
			  ResponseEntity<String> responseEntity = new ResponseEntity<>("deleted ExpectedSkill-"+expectedSkillId, HttpStatus.CREATED);
			 log.info("End::deleteExpectedSkill");

			return responseEntity;
		}
		catch (Exception e) {
			throw new ExpectedSkillIdNotFound("invalid id");
		}
		
	}
	
	//---------fetch all EXPECTED SKILLS---------
		@GetMapping(path = "/fetchAllExpectedSkills")
		 public ResponseEntity<List<ExpectedSkillBean>> fetchAllManagers() throws ExpectedSkillIdNotFound
		{
			List<ResponseEntity> beans=new ArrayList<>();
			try {
				 log.info("Start::fetchAllManagers");
				 List<ExpectedSkillBean> allManagerDetails = expectedSkillService.getAllExpectedSkillsDetails();
				 ResponseEntity<List<ExpectedSkillBean>> responseEntity = new ResponseEntity<>(allManagerDetails, HttpStatus.OK);
				 log.info("End::getManagerDetails");
	           return responseEntity;
			 }
			 catch (Exception e) {
					throw new ExpectedSkillIdNotFound("no records found");
			}	 
			
		}
	
}
