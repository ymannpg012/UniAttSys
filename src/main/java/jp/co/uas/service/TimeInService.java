package jp.co.uas.service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.uas.entities.TimeIn;
import jp.co.uas.entities.TimeOut;
import jp.co.uas.entities.UserCredentials;
import jp.co.uas.model.TimeInObject;
import jp.co.uas.model.TimeOutObject;
import jp.co.uas.repository.TimeInRepository;
import jp.co.uas.repository.TimeOutRepository;
import jp.co.uas.repository.ApplicantRepository;

@Service
public class TimeInService {
	
	@Autowired
	CurrentUserService currentUser;
	
	@Autowired
	UtilityService util;
	
	private final TimeInRepository time_in;
	private final TimeOutRepository time_out;
	private final ApplicantRepository user;
	
	public TimeInService(TimeInRepository time_in, TimeOutRepository time_out ,ApplicantRepository user) {
		this.time_in = time_in;
		this.time_out = time_out;
		this.user = user;
	}
	
	@Transactional
	public TimeIn recordTimeIn() {
		
		TimeIn timeIn = new TimeIn();
		
		UserCredentials userID = user.findById(currentUser.getCurrentUserId()).orElseThrow();
		
		if(userID != null) {
			
			timeIn.setUser(userID);
			timeIn.setTimeIn(OffsetDateTime.now(ZoneId.of("Asia/Manila")));
			
			time_in.save(timeIn);
			
			return time_in.save(timeIn);	
		}
		
		return time_in.save(timeIn);
	}
	
	public List<Map<String, String>> listTimeIns(){
		
		System.out.println(currentUser.getCurrentUserId());
		
		List<TimeIn> records = time_in.findByUserUserUUID(currentUser.getCurrentUserId());
		
		List<Map<String, String>> result = records.stream().map(record -> {
											
																	Map<String, String> map = new HashMap<>();
																	
																	 map.put("timeInDate", util.dateFormatter(record.getTimeIn()));
																     map.put("timeInTime", util.timeFormatter(record.getTimeIn()));
											
																     return map;
																     
																}).toList();
		
		System.out.println(records + "this");
		
		return result;
		
	}
	
	public boolean isTimeInAllowed() {
		
		Instant fetchedLastTimeIn = time_in.findLastTimeInDate(currentUser.getCurrentUserId());
		
		if(fetchedLastTimeIn == null) {
			return true;
		}
		
		OffsetDateTime processedLastTimeIn = fetchedLastTimeIn.atZone(ZoneId.systemDefault()).toOffsetDateTime();
		
		String dateNow = util.dateFormatter(OffsetDateTime.now(ZoneId.of("Asia/Manila")));
		String lastTimeIn = util.dateFormatter(processedLastTimeIn);
		
		System.out.println("now: " + dateNow + " last: " + lastTimeIn);
		
		if(!dateNow.equals(lastTimeIn)) {
			return true;
		}	
		
		takeLastTimeInID();
		
		return false;
	}
	
	public TimeOutObject takeLastTimeInID() {
		
		TimeOutObject timeOut = new TimeOutObject();
		
		UUID fetchedLastTimeInID = time_in.findLastTimeInID(currentUser.getCurrentUserId());
		
		System.out.println("timeOutOrigin: " + timeOut.getTimeInOrigin());
		
		timeOut.setTimeInOrigin(fetchedLastTimeInID);
		
		return timeOut;
		
	}
	
	public TimeIn takeTimeInOrigin() {
		
		TimeOutObject timeOut = takeLastTimeInID();
		
		TimeIn timeInOrigin = time_in.findByTimeInID(timeOut.getTimeInOrigin());
		
		return timeInOrigin;
		
	}
	
	public TimeInObject takeLastTimeIn() {
		
		Instant fetchedLastTimeIn = time_in.findLastTimeInDate(currentUser.getCurrentUserId());
		
		OffsetDateTime processedLastTimeIn = fetchedLastTimeIn.atZone(ZoneId.systemDefault()).toOffsetDateTime();
		
		TimeInObject timeIn = new TimeInObject(processedLastTimeIn);
		
		String lastTimeInDate = util.dateFormatter(processedLastTimeIn);
		String lastTimeInTime = util.timeFormatter(processedLastTimeIn);
		
		timeIn.setLastTimeInDate(lastTimeInDate);
		timeIn.setLastTimeInTime(lastTimeInTime);
		
		return timeIn;
	}
	
	
}
