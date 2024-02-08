package com.training.service;

import java.util.List;

import com.training.bean.TrainingBean;
import com.training.exception.IdNotFoundException;
import com.training.exception.RecordNotFoundException;

public interface TrainingService {
	void save(TrainingBean trainingbean) throws NullPointerException;
	
	void updateStatus(long training_id, String status)throws IdNotFoundException;

	List<TrainingBean> getAll() throws RecordNotFoundException;
	
	TrainingBean getById(long training_id) throws IdNotFoundException;
}
