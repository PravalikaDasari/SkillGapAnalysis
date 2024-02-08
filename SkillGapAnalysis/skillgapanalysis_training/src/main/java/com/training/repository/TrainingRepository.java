package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.training.entity.TrainingEntity;

import jakarta.transaction.Transactional;
@Transactional
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long>{

	@Modifying
	@Query(value="update training set status=:status where training_id=:training_id",nativeQuery=true)
	void updateStatus(long training_id,String status);

}