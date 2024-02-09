package com.skill.bean;

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
@Builder
public class ExpectedSkillBean {

	private long sno;
	private String designationId;
	private String skillId;
	private String expectedProficiency;
	private String status;
}
