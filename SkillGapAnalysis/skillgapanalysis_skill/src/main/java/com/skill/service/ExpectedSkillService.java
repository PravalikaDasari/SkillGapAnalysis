package com.skill.service;

import java.util.List;

import com.skill.bean.ExpectedSkillBean;
import com.skill.exception.ExpectedSkillIdNotFound;

public interface ExpectedSkillService {

	public ExpectedSkillBean insertExpectedSkill(ExpectedSkillBean bean);
	public void deleteExpectedSkill(long expectedSkillId, String status) throws ExpectedSkillIdNotFound ;
	public List<ExpectedSkillBean> getAllExpectedSkillsDetails() throws ExpectedSkillIdNotFound ;
}
