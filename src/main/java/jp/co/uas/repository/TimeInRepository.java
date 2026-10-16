package jp.co.uas.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.uas.entities.TimeIn;
import jp.co.uas.model.TimeInList;
import jp.co.uas.model.TimeListObject;

public interface TimeInRepository extends JpaRepository<TimeIn, UUID> {

	List<TimeIn> findByUserUserUUID(UUID user);
	
	@Query(value = """
			SELECT user_id, time_in_id, time_in, time_out
			FROM time_in ti
			LEFT JOIN time_out tu
			ON ti.time_in_id = tu.time_in_origin 
			WHERE time_in >= :startDate
			AND time_in <  :endDate
			AND user_id = :userId
			ORDER BY ti.time_in
			""",
			nativeQuery = true)
	List<TimeListObject> listTimeByRange(@Param("startDate")LocalDate startDate,
										 @Param("endDate")LocalDate endDate,
										 @Param("userId")UUID userId);
	
	@Query(value="""
			SELECT time_in, time_in_id 
			FROM time_in 
			WHERE time_in >= :startDate 
			AND time_in <  :endDate;
			""",
			nativeQuery = true)
	List<TimeInList> findTimeInsWithinRange(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
	
	@Query(value = """ 
			SELECT time_in
			FROM time_in
			WHERE user_id = :user_id
			ORDER BY time_in 
			DESC LIMIT 1 
			""", 
			nativeQuery = true)
	Instant findLastTimeInDateByID(@Param("user_id") UUID user);
	
	@Query(value = """ 
			SELECT time_in_id
			FROM time_in
			WHERE user_id = :user_id
			ORDER BY time_in 
			DESC LIMIT 1 
			""", 
			nativeQuery = true)
	UUID findLastTimeInID(@Param("user_id") UUID user);

	TimeIn findByTimeInID(UUID timeInOrigin);
	
}
