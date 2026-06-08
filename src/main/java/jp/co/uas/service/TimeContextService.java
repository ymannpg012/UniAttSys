package jp.co.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.uas.model.TimeContextObject;
import jp.co.uas.model.TimeInObject;
import jp.co.uas.model.TimeOutObject;

@Service
public class TimeContextService {

	@Autowired
	TimeInService timeInService;
	
	@Autowired
	TimeOutService timeOutService;
	
	public TimeContextObject createTimeContext() {
		
		TimeContextObject timeContext = new TimeContextObject();
		
		timeContext.setHasTimeInToday(!timeInService.isTimeInAllowed());
		
		System.out.println(timeContext.getHasTimeInToday());
		
		if(timeContext.hasTimeInToday()) {
			
			TimeInObject timeIn = timeInService.takeLastTimeIn();
			
			timeContext.setLatestTimeInDate(timeIn.getLastTimeInDate());
			timeContext.setLatestTimeInTime(timeIn.getLastTimeInTime());
			
		}
		
		timeContext.setHasTimeOutToday(!timeOutService.isTimeOutAvailable());
		
		System.out.println("hasTimeOut: " + timeContext.getHasTimeOutToday());
		
		if(timeContext.hasTimeOutToday()) {
			
			TimeOutObject timeOut = timeOutService.takeLastTimeOut();
			
			timeContext.setLatestTimeOutDate(timeOut.getLatestTimeOutDate());
			timeContext.setLatestTimeOutTime(timeOut.getLatestTimeOutTime());
			
		}
		
		return timeContext;
		
	}
	
}
