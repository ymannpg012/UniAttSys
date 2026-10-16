package jp.co.uas.service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {

	public String dateFormatter(OffsetDateTime data) {
		
		if (data == null) {
	        return null;
	    }
		
		return data.toLocalDate().toString();
	
	}
	
	public String timeFormatter(OffsetDateTime data) {
		
		if (data == null) {
	        return null;
	    }
		
	    return data
	    		.atZoneSameInstant(ZoneId.of("Asia/Manila"))
	    		.format(DateTimeFormatter.ofPattern("h:mm a"));
	
	}
	
	public String instantDateFormatter(Instant date) {
		
		if (date == null) {
	        return null;
	    }
		
		OffsetDateTime convertedDate = date.atZone(ZoneId.systemDefault()).toOffsetDateTime();
		return dateFormatter(convertedDate);
		
	}
	
	public String instantTimeFormatter(Instant date) {
		
		if (date == null) {
	        return null;
	    }
		
		OffsetDateTime convertedTime = date.atZone(ZoneId.systemDefault()).toOffsetDateTime();
		return timeFormatter(convertedTime);
		
	}
	
}
