package com.training.service;

import java.util.List;

import com.training.bean.TrainingBean;
import com.training.exception.IdNotFoundException;
import com.training.exception.RecordNotFoundException;

public interface TrainingService {
	void save(TrainingBean trainingbean) throws NullPointerException;

	void deleteById(long training_id) throws NullPointerException;

	void update(TrainingBean trainingbean, TrainingBean training_id);

	List<TrainingBean> getAll() throws RecordNotFoundException;
	
	TrainingBean getById(long training_id) throws IdNotFoundException;
}
