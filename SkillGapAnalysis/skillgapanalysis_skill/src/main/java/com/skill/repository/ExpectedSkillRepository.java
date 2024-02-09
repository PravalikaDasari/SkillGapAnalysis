package com.skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.skill.entity.ExpectedSkillEntity;

public interface ExpectedSkillRepository extends JpaRepository<ExpectedSkillEntity, Long>{

	@Modifying
	@Query(value="update expected_skill set status=:status where sno=:expectedSkillId",nativeQuery = true)
	public void deleteExpectedSkill(long expectedSkillId, String status);
}
