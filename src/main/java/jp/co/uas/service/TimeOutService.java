package jp.co.uas.service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.uas.entities.TimeIn;
import jp.co.uas.entities.TimeOut;
import jp.co.uas.model.TimeOutObject;
import jp.co.uas.repository.TimeOutRepository;

@Service
public class TimeOutService {
	
	@Autowired
	TimeInService timeInService;
	
	@Autowired
	UtilityService util;
	
	private final TimeOutRepository timeOutRepo;
	
	public TimeOutService(TimeOutRepository timeOutRepo) {
		this.timeOutRepo = timeOutRepo;
	}
	
	public boolean isTimeOutAvailable() {

		TimeOutObject timeOut = timeInService.takeLastTimeInID();
		TimeOutObject lastTimeOut = takeLastTimeOut();
		
		if(timeOut.getTimeInOrigin() != null && lastTimeOut.getLatestTimeOutDate() == null) {
			return true;
		}
		return false;
		
	}
	
	public TimeOutObject takeLastTimeOut() {
		
		TimeOutObject timeOut = timeInService.takeLastTimeInID();
		
		Instant fetchedLastTimeOut = timeOutRepo.findLastTimeOut(timeOut.getTimeInOrigin());
		
		if(fetchedLastTimeOut == null && timeOut.getLatestTimeOutDate() == null) {
			return timeOut;
		}
		
		OffsetDateTime processedLastTimeOut = fetchedLastTimeOut.atZone(ZoneId.systemDefault()).toOffsetDateTime();
		
		String lastTimeOutDate = util.dateFormatter(processedLastTimeOut);
		String lastTimeOutTime = util.timeFormatter(processedLastTimeOut);
		
		timeOut.setLatestTimeOutDate(lastTimeOutDate);
		timeOut.setLatestTimeOutTime(lastTimeOutTime);
		
		return timeOut;
		
	}
	
	@Transactional
	public TimeOut launchTimeOut(TimeIn timeInOrigin){
		
		TimeOut timeOut = new TimeOut();
		
		timeOut.setTimeOut(OffsetDateTime.now(ZoneId.of("Asia/Manila")));
		timeOut.setTimeInOrigin(timeInOrigin);
		
		return timeOutRepo.save(timeOut);
		
	}
}
