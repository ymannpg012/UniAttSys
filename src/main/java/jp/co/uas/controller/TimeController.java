package jp.co.uas.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.uas.service.TimeInService;
import jp.co.uas.service.TimeOutService;
import jp.co.uas.service.helper.CurrentUserService;

@Controller
public class TimeController {
	
	@Autowired
	CurrentUserService currentUserService;
	
	@Autowired
	TimeInService timeInService;

	@Autowired
	TimeOutService timeOutService;
	
	
	@PostMapping("/time-in/save")
	@ResponseBody
	public boolean setTimeIn(Model model) {
		
		System.out.println("test");
		
		UUID userId = currentUserService.getCurrentUserId();
		
		if(userId == null ) {
			return false;
		}
		
		if(timeInService.isTimeInAllowed()) {
		
			timeInService.recordTimeIn();
			
		}
		
		
		return true;
		
	}
	
	@PostMapping("/time-out/save")
	@ResponseBody
	public boolean setTimeOut(Model model) {
		
		System.out.println("test out");
		
		if(timeOutService.isTimeOutAvailable()) {
			
			
			timeOutService.launchTimeOut(timeInService.takeTimeInOrigin());
			
			return true;
		}
		
		return false;
		
	}
	
}
