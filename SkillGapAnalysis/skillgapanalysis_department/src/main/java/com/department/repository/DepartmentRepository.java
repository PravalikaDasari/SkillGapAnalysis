package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.department.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String>{
	@Modifying
	@Query(value="update department set status=:status where department_id=:departmentId",nativeQuery = true)
	void delete(String departmentId, String status);

}
