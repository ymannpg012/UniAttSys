package jp.co.uas.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import jp.co.uas.entities.UserCredentials;

public interface ApplicantRepository extends JpaRepository<UserCredentials, UUID> {
	
	UserCredentials findByUsername(String username);

}
