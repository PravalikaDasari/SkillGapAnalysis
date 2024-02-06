package com.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skill.entity.SkillEntity;

public interface SkillRepository  extends JpaRepository<SkillEntity, String>  {


}
