package jp.co.uas.repository;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.uas.entities.TimeIn;
import jp.co.uas.entities.TimeOut;

public interface TimeOutRepository extends JpaRepository<TimeOut, UUID> {

	@Query(value = """
			select time_out from time_out where time_in_origin = :time_in_origin
			""", 
			nativeQuery=true)
	Instant findLastTimeOut(@Param("time_in_origin") UUID timeIn);
	
}
