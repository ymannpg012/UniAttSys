package jp.co.uas.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.uas.entities.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, UUID>{

	@Query(value = """
			SELECT * 
			FROM payroll_cycle pc 
			LEFT JOIN employment_roles ec 
			ON pc.assign_to_employment_role = ec.role_id 
			WHERE ec.role_name =:assignment
			""", 
			nativeQuery = true)
	Payroll findByAssignment(@Param("assignment") String assignment);
	
}
