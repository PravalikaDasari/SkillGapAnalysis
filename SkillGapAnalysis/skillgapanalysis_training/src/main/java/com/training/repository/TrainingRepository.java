package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.TrainingEntity;

public interface TrainingRepository extends JpaRepository<TrainingEntity, Long>{

}