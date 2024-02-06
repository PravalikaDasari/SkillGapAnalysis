package com.training.entity;

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
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="training")
public class TrainingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="training_id")
	private long training_id;
	@Column(name="skill_id")
	 private String skill_id;
	@Column(name="training_provider")
	 private String training_provider;
	@Column(name="mode")
	 private String mode;
	@Column(name="employee_id")
	 private long employee_id;
	@Column(name="status")
	 private String status;
}
