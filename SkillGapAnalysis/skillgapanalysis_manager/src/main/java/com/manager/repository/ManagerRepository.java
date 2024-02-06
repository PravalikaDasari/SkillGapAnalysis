package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manager.entity.ManagerEntity;

public interface ManagerRepository  extends JpaRepository<ManagerEntity, Long>{

}
