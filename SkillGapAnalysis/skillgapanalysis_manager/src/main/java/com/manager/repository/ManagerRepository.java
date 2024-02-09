package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.manager.entity.ManagerEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface ManagerRepository  extends JpaRepository<ManagerEntity, Long>{

	@Modifying
	@Query(value="update manager set status=:status where manager_id=:managerId",nativeQuery = true)
	public void deleteManagerDetails(long managerId,String status) ;
	
	@Modifying
	@Query(value="update manager set image=:profile where manager_id=:managerId" , nativeQuery = true)
	void updateManagerProfile(long managerId, byte[] profile);
	
	
	@Query(value="select manager_id from manager where manager_email=:managerEmail" , nativeQuery = true)
	public long getByManagerEmail(String managerEmail);
	
	@Modifying
	@Query(value="update manager set manager_password=:password where manager_email=:managerEmail" , nativeQuery = true)
	void updateManagerPassword(String managerEmail,String password);
	
}
