package jp.co.uas.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.uas.entities.TimeOut;
import jp.co.uas.model.TimeOutList;

public interface TimeOutRepository extends JpaRepository<TimeOut, UUID> {

	@Query(value = """
			SELECT time_out 
			FROM time_out 
			WHERE time_in_origin = :time_in_origin
			""", 
			nativeQuery=true)
	Instant findLastTimeOut(@Param("time_in_origin") UUID timeIn);
	
	@Query(value="""
			SELECT time_out_id, time_out, time_in_origin 
			FROM time_out 
			WHERE time_out >= :startDate 
			AND time_out <  :endDate;
			""",
			nativeQuery = true)
	List<TimeOutList> findTimeOutsWithinRange(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
	
}
