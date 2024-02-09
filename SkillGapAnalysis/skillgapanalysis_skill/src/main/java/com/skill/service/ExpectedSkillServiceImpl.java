package com.skill.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.skill.bean.ExpectedSkillBean;
import com.skill.entity.ExpectedSkillEntity;
import com.skill.exception.ExpectedSkillIdNotFound;
import com.skill.repository.ExpectedSkillRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class ExpectedSkillServiceImpl implements ExpectedSkillService{

	@Autowired
	ExpectedSkillRepository expectedSkillRepository;
	
	public  ExpectedSkillBean convertEntityToBean(ExpectedSkillEntity entity)
	{
		ExpectedSkillBean bean=new ExpectedSkillBean();
		bean.setSno(entity.getSno());
		bean.setDesignationId(entity.getDesignationId());
		bean.setSkillId(entity.getSkillId());
		bean.setExpectedProficiency(entity.getExpectedProficiency());
		bean.setStatus(entity.getStatus());
		return bean;
	}
	
	public  ExpectedSkillEntity convertBeanToEntity(ExpectedSkillBean bean)
	{
		ExpectedSkillEntity entity=new ExpectedSkillEntity();
		entity.setSno(bean.getSno());
		entity.setDesignationId(bean.getDesignationId());
		entity.setSkillId(bean.getSkillId());
		entity.setExpectedProficiency(bean.getExpectedProficiency());
		entity.setStatus(bean.getStatus());
		return entity;
	}
	
	@Override
	public ExpectedSkillBean insertExpectedSkill(ExpectedSkillBean bean) {
		try {
			if(bean!=null)
			{
			  ExpectedSkillEntity entity = convertBeanToEntity(bean);
			  ExpectedSkillEntity entity2 = expectedSkillRepository.save(entity);
			  ExpectedSkillBean bean2 = convertEntityToBean(entity2);
			  return bean2;
			}
			else
			{
				throw new NullPointerException("ExpectedSkill bean is null");
			}
		}
		catch (Exception e) {
			throw new NullPointerException("ExpectedSkill bean is null");
		}
		
	}

	@Override
	public void deleteExpectedSkill(long expectedSkillId, String status) throws ExpectedSkillIdNotFound {
		try {
			boolean existsById = expectedSkillRepository.existsById(expectedSkillId);
			log.info("---"+existsById);
			if(existsById)
			{
			   expectedSkillRepository.deleteExpectedSkill(expectedSkillId, status);
			}
			else
			{
				throw new ExpectedSkillIdNotFound("invalid id");
			}
		}
		catch (Exception e) {
			throw new ExpectedSkillIdNotFound("invalid id");
		}
		
	}

	@Override
	public List<ExpectedSkillBean> getAllExpectedSkillsDetails() throws ExpectedSkillIdNotFound {
		List<ExpectedSkillBean> beans=new ArrayList<>();
		try {
			List<ExpectedSkillEntity> findAll = expectedSkillRepository.findAll();
			log.info(""+findAll);
			if(findAll!=null)
			{
			  for (ExpectedSkillEntity expectedSkillEntity : findAll) {
				  ExpectedSkillBean bean = convertEntityToBean(expectedSkillEntity);
				  beans.add(bean);
			  }
			}
			else
			{
				throw new ExpectedSkillIdNotFound("no records found");
			}
		}
		catch (Exception e) {
			throw new ExpectedSkillIdNotFound("no records found");
		}
		return beans;
	}

	

}
