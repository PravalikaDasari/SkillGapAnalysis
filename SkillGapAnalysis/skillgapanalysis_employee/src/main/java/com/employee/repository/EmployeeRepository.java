package com.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.employee.entity.EmployeeEntity;

import jakarta.transaction.Transactional;

@Transactional
public interface EmployeeRepository extends JpaRepository<EmployeeEntity , Long>
{
	Optional<EmployeeEntity>findByEmail(String email);

	@Modifying
	@Query(value="update employee set status=:status where employee_email=:email",nativeQuery = true)
	void updateStatus(String email, String status);

	@Modifying
	@Query(value="update employee set image=:profile where employee_email=:email" , nativeQuery = true)
	void updateProfile(String email, byte[] profile);
}
