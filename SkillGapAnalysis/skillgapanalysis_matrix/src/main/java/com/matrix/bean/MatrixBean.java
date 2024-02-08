package com.matrix.bean;

import javax.print.DocFlavor.BYTE_ARRAY;

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
public class MatrixBean
{
	private Long sno;

	private Long employee_id;
		
	private String skill_id;
	
	private String proficiency;
	
	private BYTE_ARRAY certification;
	 
	private String status;
}
