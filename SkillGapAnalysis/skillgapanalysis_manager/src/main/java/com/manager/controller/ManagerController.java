package com.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.manager.bean.ManagerBean;
import com.manager.exception.ManagerNotFound;
import com.manager.service.ManagerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/manager")
@Slf4j
public class ManagerController {


	@Autowired
	ManagerService managerService;
	
	
	
	//------------------------inserting-----------------------
	@PostMapping(path="/insertManager")
    public ResponseEntity<ManagerBean> insertManagerDetails(@RequestBody ManagerBean bean) {

	try {
		 log.info("Start::insertMangerDetails");
		 log.info("ManagerBean-"+bean);

		   ManagerBean bean2 = managerService.insertManagerDetails(bean);
		   ResponseEntity<ManagerBean> managerBean = new ResponseEntity<>(bean2, HttpStatus.CREATED);
			 log.info("End::insertMangerDetails");
			return managerBean;

	}
	catch (Exception e) {
		throw new NullPointerException("ManagerBean is null");
	}
 }
	
	
	
	//-------------------------soft deletion------------------------
	@GetMapping(path="/deleteManager/{managerId}")
    public ResponseEntity<String> deleteManagerDetails(@PathVariable long managerId) throws ManagerNotFound {

	 try {
		 log.info("Start::deleteManagerDetails");
		 log.info("ManagerBean-"+managerId);

		   managerService.deleteManagerDetails(managerId,"Inactive");
		    ResponseEntity<String> responseEntity = new ResponseEntity<>(managerId+"-manager record deleted", HttpStatus.OK);
			 log.info("End::deleteManagerDetails");
			return responseEntity;
	 }
	 catch (Exception e) {
			throw new ManagerNotFound("invalid managerId");
	}

 }
	
	
	
	
	//-----------------------get  manager details by id-----------------
	@GetMapping(path="/getManager/{managerId}")
	 public ResponseEntity<ManagerBean> getManagerDetails(@PathVariable long managerId) throws ManagerNotFound
	 {
		 try {
			 log.info("Start::getManagerDetails");
			 log.info("ManagerBean-"+managerId);
			 ManagerBean bean = managerService.getManagerDetailsById(managerId);
			 ResponseEntity<ManagerBean> managerBean = new ResponseEntity<>(bean, HttpStatus.CREATED);
			 log.info("End::getManagerDetails");
            return managerBean;
		 }
		 catch (Exception e) {
				throw new ManagerNotFound("invalid managerId");
		}	 
		 
	 }
	
	
	
	
	
	//---------------------------fetch all managers------------------
	@GetMapping(path = "/fetchAllManagers")
	 public ResponseEntity<List<ManagerBean>> fetchAllManagers() throws ManagerNotFound
	{
		List<ResponseEntity> beans=new ArrayList<>();
		try {
			 log.info("Start::fetchAllManagers");
			 List<ManagerBean> allManagerDetails = managerService.getAllManagerDetails();
			 ResponseEntity<List<ManagerBean>> responseEntity = new ResponseEntity<>(allManagerDetails, HttpStatus.OK);
			 log.info("End::getManagerDetails");
           return responseEntity;
		 }
		 catch (Exception e) {
				throw new ManagerNotFound("invalid managerId");
		}	 
		
	}
	
	//----------------update manager profile image-------------
	
	@GetMapping(path="/updateManagerProfile/{managerId}")
	 public ResponseEntity<String> updateManagerProfile(@PathVariable long managerId,@RequestParam("image") MultipartFile image) throws ManagerNotFound
	 {
		log.info("Start::updateManagerProfile");
		log.info("managerId-"+managerId);
	 try {
         if (!image.isEmpty()) 
         {
         	String imageName = image.getOriginalFilename();
             byte[] profile = image.getBytes();
             managerService.updateProfileImage(profile, managerId);
     		log.info("End::updateManagerProfile");
             return ResponseEntity.ok("image uploaded successfully: " + imageName);
         } else {
             return ResponseEntity.badRequest().body("image is empty or not provided.");
         }
     } catch (IOException e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
     }
		 
	 }
	
	//------------update password--------------
	@PutMapping("/updatePassword")
	public ResponseEntity<String> updateManagerPassword(@RequestParam String managerEmail,
	        @RequestParam String password) throws ManagerNotFound {
 		log.info("Start::updateManagerPassword");
		log.info("managerEmail-"+managerEmail);
		log.info("manager New Password-"+password);

		try {
			if(managerEmail!=null)
			{
			  managerService.updateManagerPassword(managerEmail, password);
			    return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);

			}
			else {
				throw new ManagerNotFound("invalid managerPassword");

			}
		}
		catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating password.");
	     }
	}
	
}
