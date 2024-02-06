package com.skill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skill.bean.SkillBean;
import com.skill.entity.SkillEntity;
import com.skill.service.SkillService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SkillController
{
	@Autowired
	private SkillService skillService;

	   @PostMapping(path="/create")
	    public ResponseEntity<SkillBean> saveSkill(@RequestBody SkillBean skillsDto) {
		   System.out.println("post mapping");

		   skillService.save(skillsDto);

			ResponseEntity<SkillBean> responseEntity = new ResponseEntity<>(skillsDto, HttpStatus.CREATED);
			return responseEntity;
	    }
	   
	@GetMapping(path = "/{skillCode}")
	public ResponseEntity<SkillEntity> get(@PathVariable String skillCode) {
		log.info("Fetching skill :", skillCode);

		SkillEntity skillsDto = skillService.get(skillCode);

		ResponseEntity<SkillEntity> responseEntity = new ResponseEntity<>(skillsDto,
				HttpStatus.OK);
		return responseEntity;
	}
	
	@PutMapping("/update/{skillCode}")
	public ResponseEntity<String> updateSkillColumn( @PathVariable String skillCode,
	        @RequestParam String columnValue) {
	    skillService.updateSkillColumnById(skillCode, columnValue);
	    return new ResponseEntity<>("Skill updated successfully", HttpStatus.OK);
	}

}

