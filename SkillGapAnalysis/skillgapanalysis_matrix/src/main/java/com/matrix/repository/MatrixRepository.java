package com.matrix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matrix.entity.MatrixEntity;

public interface MatrixRepository  extends JpaRepository<MatrixEntity, Long>  {


}
