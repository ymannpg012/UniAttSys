package jp.co.uas.service;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

	public String dateFormatter(OffsetDateTime data) {
		
		return data.toLocalDate().toString();
	
	}
	
	public String timeFormatter(OffsetDateTime data) {
		
	    return data
	    		.atZoneSameInstant(ZoneId.of("Asia/Manila"))
	    		.format(DateTimeFormatter.ofPattern("h:mm a"));
	
	}
	
}
