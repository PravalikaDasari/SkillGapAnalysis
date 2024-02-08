package com.matrix.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.matrix.bean.EmployeeBean;
import com.matrix.bean.MatrixBean;
import com.matrix.entity.MatrixEntity;
import com.matrix.repository.MatrixRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MatrixServiceImpl implements MatrixService 
{
	@Autowired
	private MatrixRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;

	public MatrixEntity convertBeanToEntity(MatrixBean bean)
	{
		log.info("convertion start");
		log.info("bean is :"+bean.toString());
		MatrixEntity entity = new MatrixEntity();
		entity.setSno(bean.getSno());
		entity.setEmployee_id(bean.getEmployee_id());
		entity.setSkill_id(bean.getSkill_id());
		entity.setProficiency(bean.getProficiency());
		entity.setCertification(bean.getCertification());
		entity.setStatus(bean.getStatus());
		log.info(entity.toString()+" convertion end");
		return entity;
	}
	
	public MatrixBean convertEntityToBean(MatrixEntity entity)
	{
		MatrixBean bean = new MatrixBean();
		bean.setSno(entity.getSno());
		bean.setEmployee_id(entity.getEmployee_id());
		bean.setSkill_id(entity.getSkill_id());
		bean.setProficiency(entity.getProficiency());
		bean.setCertification(entity.getCertification());
		bean.setStatus(entity.getStatus());
		return bean;
		
	}

	@Override
	public MatrixBean saveMatrix(MatrixBean bean) throws NullPointerException {
		log.info("service start");
		try {
			MatrixEntity entity = convertBeanToEntity(bean);
			MatrixEntity matrixEntity = repository.save(entity);
			return convertEntityToBean(matrixEntity);
		}catch(NullPointerException e)
		{
			throw new NullPointerException(e.getMessage());
		}
	}

	@Override
	public void updateStatus(Long eId, String status) {
		
		Object emps=restTemplate.getForObject("http://localhost:8084/skillgap/get", Object.class);
		log.info(" "+emps);
		
		
		
		
		Optional<MatrixEntity> sportsOptional = repository.findById(eId);
		MatrixEntity matrix = sportsOptional.get();

//		log.info("Medicine - {}", sports);

		String url = "http://localhost:8084/skillgap/" + matrix.getEmployee_id();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

//		log.info("URL - {}", url);

		// Input entity
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<EmployeeBean> responseEntity = restTemplate.exchange(url,
				HttpMethod.GET, httpEntity, EmployeeBean.class);
		Long empid = responseEntity.getBody().getEmpId();

		MatrixBean matrixDto = matrixDto.builder().sno(matrix.getSno())
				.employee_id(empid).skill_id(skillid)
				.proficiency(matrix.getProficiency()).certification(matrix.getCertification()).status.(matrix.getStatus())	;
	
//		
//		
//	
		
//		Optional<EmployeeEntity> findByacc = repository.findByEmail(email);
		
		
//		EmployeeBean employee = repository.findById(eId);
//
//				if (employee.isPresent()) {
//				
//					employee;
////					   
//				}
//						else throw new EmployeeNotFoundException("Wrong email id ");
//			}catch(EmployeeNotFoundException e)
//				{ 
//				   throw new EmployeeNotFoundException("Wrong Email id ");
//				}
//
//				} else {
//					throw new IllegalArgumentException("Employee not found  with " + eId +" Employee id");
//				}
//		
//	}
		
	}
	


	
}
