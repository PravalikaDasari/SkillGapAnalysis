package com.skill.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill")
public class SkillEntity implements Serializable{

 static final long serialVersionUID = 1L;

	@Id
	@Column(name = "skill_id")
	private String skillCode;

	@Column(name = "skill_name")
	private String skillName;
	
	@Column(name = "level_expected")
	private String levelExpected;
	
	@Column(name = "status")
	private String status;
	
}
