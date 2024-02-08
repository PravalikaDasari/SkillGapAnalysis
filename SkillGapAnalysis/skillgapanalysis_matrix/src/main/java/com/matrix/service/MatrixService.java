package com.matrix.service;

import java.util.Optional;

import com.matrix.bean.MatrixBean;

public interface MatrixService 
{
	 MatrixBean saveMatrix(MatrixBean bean) throws NullPointerException;
	void updateStatus(Long eId,String password);

	
}
