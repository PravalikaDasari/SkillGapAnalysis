package com.department.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DesignationBean {
	private Long designationId;
	private String designationName;
	private String departmentId;
	private String status;
}
