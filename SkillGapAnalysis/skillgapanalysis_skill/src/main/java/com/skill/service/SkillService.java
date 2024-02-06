package com.skill.service;

import com.skill.bean.SkillBean;
import com.skill.entity.SkillEntity;

public interface SkillService {

	 void save(SkillBean patientsDto);

	 SkillEntity get(String skillCode);
	
	 void updateSkillColumnById(String skillCode,String columnValue);

	
}
