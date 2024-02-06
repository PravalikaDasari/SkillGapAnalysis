package com.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.department.entity.DesignationEntity;

public interface DesignationRepository extends JpaRepository<DesignationEntity, Long>{

	@Modifying
	@Query(value="update status set status=:status where designation_id=:designationId",nativeQuery = true)
	void delete(Long designationId, String status);

}
