package com.matrix.entity;

import java.io.Serializable;

import javax.print.DocFlavor.BYTE_ARRAY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "skill_matrix")
public class MatrixEntity implements Serializable{

 static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private Long sno;

	@Column(name = "employee_id")
	private Long employee_id;
	
	@Column(name = "skill_id")
	private String skill_id;
	
	@Column(name = "proficiency")
	private String proficiency;
	
	@Column(name = "certification")
	private BYTE_ARRAY certification;
	
	@Column(name = "status")
	private String status;
	
}
