package jp.co.uas.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.uas.entities.UserInformation;

public interface UserInformationRepository extends JpaRepository<UserInformation, UUID> {

	@Query(value="""
			SELECT *
			FROM user_information
			WHERE employment_role =:employmentRole
			""", nativeQuery=true)
	List<UserInformation> findByEmploymentRole(@Param("employmentRole") UUID employmentRole);
	
}
