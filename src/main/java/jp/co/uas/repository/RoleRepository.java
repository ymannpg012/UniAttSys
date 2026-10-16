package jp.co.uas.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.uas.entities.Roles;

public interface RoleRepository extends JpaRepository<Roles, UUID> {

	Roles findByRoleName(String rolename);
	
	@Query(value = """
			SELECT *
			FROM employment_roles
			""",
			nativeQuery = true)
	List<Roles> findRoleNames();
	
}
