package com.skill.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skill.bean.SkillBean;
import com.skill.entity.SkillEntity;
import com.skill.repository.SkillRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public void save(SkillBean skillDto) {
		SkillEntity skillEntity = new SkillEntity();
		skillEntity.setSkillCode(skillDto.getSkillCode());
		skillEntity.setSkillName(skillDto.getSkillName());
		skillEntity.setStatus(skillDto.getStatus());

		skillRepository.save(skillEntity);
	}

	@Override
	public SkillEntity get(String skillCode) {
		return skillRepository.findById(skillCode)
				.orElseThrow(() -> new IllegalArgumentException("Skill not found with id-" + skillCode));
	}

	@Override
	public void updateSkillColumnById(String skillCode, String columnValue) {
		Optional<SkillEntity> skillOptional = skillRepository.findById(skillCode);

		if (skillOptional.isPresent()) {
			SkillEntity skillEntity = skillOptional.get();

//			// Update the specific column
//			switch (columnName.toLowerCase()) {
//			case "levelexpected":
//				skill.setLevelExpected(columnValue);
//				break;
//			case "status":
				skillEntity.setStatus(columnValue);
//				break;
//			default:
			

			// Saving
			skillRepository.save(skillEntity);
		} else {
			throw new IllegalArgumentException("Skill not found with ID: " + skillCode);
		}
	}
}
