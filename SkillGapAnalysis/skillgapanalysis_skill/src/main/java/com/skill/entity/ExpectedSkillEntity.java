package com.skill.entity;

import com.skill.bean.ExpectedSkillBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="expected_skill")
public class ExpectedSkillEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private long sno;
	
	@Column(name = "designation_id")
	private String designationId;
	
	@Column(name = "skill_id")
	private String skillId;
	
	@Column(name = "expected_proficiency")
	private String expectedProficiency;
	
	@Column(name = "status")
	private String status;
}
