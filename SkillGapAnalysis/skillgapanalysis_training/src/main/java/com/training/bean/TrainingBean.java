package com.training.bean;
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
public class TrainingBean {
 private long training_id;
 private String skill_id;
 private String training_provider;
 private String mode;
 private long employee_id;
 private String status;
 
}
