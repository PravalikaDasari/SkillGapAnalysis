package com.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.bean.TrainingBean;
import com.training.entity.TrainingEntity;
import com.training.exception.IdNotFoundException;
import com.training.exception.RecordNotFoundException;
import com.training.repository.TrainingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingRepository trainingRepository;

	public TrainingEntity convertBeanToEntity(TrainingBean trainingbean) {
		TrainingEntity entity = new TrainingEntity();
		entity.setEmployee_id(trainingbean.getEmployee_id());
		entity.setMode(trainingbean.getMode());
		entity.setSkill_id(trainingbean.getSkill_id());
		entity.setStatus(trainingbean.getStatus());
		entity.setTraining_id(trainingbean.getTraining_id());
		entity.setTraining_provider(trainingbean.getTraining_provider());
		return entity;
	}

	public TrainingBean convertEntityToBean(TrainingEntity trainingentity) {
		TrainingBean bean = new TrainingBean();
		bean.setEmployee_id(trainingentity.getEmployee_id());
		bean.setMode(trainingentity.getMode());
		bean.setSkill_id(trainingentity.getSkill_id());
		bean.setStatus(trainingentity.getStatus());
		bean.setTraining_id(trainingentity.getTraining_id());
		bean.setTraining_provider(trainingentity.getTraining_provider());
		return bean;
	}

	@Override
	public void save(TrainingBean trainingbean) throws NullPointerException {
		try {
			if (trainingbean != null) {
				TrainingEntity entity1 = convertBeanToEntity(trainingbean);
				trainingRepository.save(entity1);
			} else {
				throw new NullPointerException("trainingbean is null");
			}
		} catch (NullPointerException exception) {
			throw new NullPointerException("trainingbean is null");
		}
	}

	@Override
	public TrainingBean getById(long training_id) throws IdNotFoundException {
		try {
			Optional<TrainingEntity> entity = trainingRepository.findById(training_id);
			TrainingBean bean = convertEntityToBean(entity.get());
			if (bean == null) {
				throw new IdNotFoundException("Invalid trainingid");
			}
			return bean;
		} catch (Exception e) {
			throw new IdNotFoundException("Invalid trainingid");
		}

	}

	@Override
	public List<TrainingBean> getAll() throws RecordNotFoundException {
		try {
			List<TrainingEntity> findall = trainingRepository.findAll();
			List<TrainingBean> list1 = new ArrayList<>();
			for (TrainingEntity entity : findall) {
				TrainingBean bean1 = convertEntityToBean(entity);
				list1.add(bean1);
			}
			return list1;
		} catch (Exception e) {
			throw new RecordNotFoundException("RecordNotFound");
		}
	}

	@Override
	public void updateStatus(long training_id, String status) throws IdNotFoundException {
		try {
			if (status != null) {
				TrainingBean bean1 = getById(training_id);
				trainingRepository.updateStatus(training_id, status);
			}

		} catch (IdNotFoundException e) {
			throw new IdNotFoundException("Record not found with this id");
		}
	}

}
