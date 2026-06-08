package jp.co.uas.repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.uas.entities.TimeIn;

public interface TimeInRepository extends JpaRepository<TimeIn, UUID> {

	List<TimeIn> findByUserUserUUID(UUID user);
	
	@Query(value = """ 
					select time_in
					from time_in
					where user_id = :user_id
					order by time_in desc limit 1 
					""", 
					nativeQuery = true)
	Instant findLastTimeInDate(@Param("user_id") UUID user);
	
	@Query(value = """ 
			select time_in_id
			from time_in
			where user_id = :user_id
			order by time_in desc limit 1 
			""", 
			nativeQuery = true)
	UUID findLastTimeInID(@Param("user_id") UUID user);

	TimeIn findByTimeInID(UUID timeInOrigin);
	
}
