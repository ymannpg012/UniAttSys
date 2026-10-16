package jp.co.uas.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.uas.model.PayrollCycleObject;
import jp.co.uas.model.TimeContextObject;
import jp.co.uas.repository.ApplicantRepository;
import jp.co.uas.service.TimeContextService;
import jp.co.uas.service.TimeInService;
import jp.co.uas.service.TimeLogsService;
import jp.co.uas.service.helper.CurrentUserService;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DashboardController {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ApplicantRepository repo;
	
	@Autowired
	TimeInService timeInService;
	
	@Autowired
	CurrentUserService currentUserService;
	
	@Autowired
	TimeContextService timeContext;

	@Autowired
	TimeLogsService timeLogs;
	
	@GetMapping("/dashboard")
	public String launchDashoard(Model model) {
		
		
		UUID userId = currentUserService.getCurrentUserId();
		
		System.out.println(userId);
		
		//model.addAttribute(userId);
		
		return "dashboard";

	}
	
	@GetMapping("/login")
	public String launchLogin(Model model) {
		
		return "loginForm";
		
	}
	
//	@GetMapping("/logs-summary")
//	public String launchSummary(Model model){
//		
//		return "summary";
//	
//	}
//	
//	@PostMapping("/logs-summary")
//	@ResponseBody
//	public List<Map<String, String>> timeInList(){
//		
//		List<Map<String, String>> records = timeInService.listTimeIns();
//		
//		System.out.println(records);
//		
//		return records;
//	}
	
	
	@GetMapping("/timeLogs")
	public String launchTimeLogs(Model model) {
		
		return "timeLogs";
		
	}
	
	@PostMapping("/timeLogs")
	@ResponseBody
	public PayrollCycleObject takePayrollCycleData(@RequestParam("assignment") String assignment) {
		
		return timeLogs.fetchPayrollCycleData(assignment);
		
	}
	
	@PostMapping("/checkTimeIn") 
	@ResponseBody
	public TimeContextObject isTimeInAllowed() {
		
		return timeContext.createTimeContext();
		
	}
	

//	@GetMapping("/{id}")
//	public Applicant getById(@PathVariable("id") UUID id) {
//	    return repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
//	}
	
}
