package jp.co.uas.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.uas.model.TimeListOutput;
import jp.co.uas.service.PayrollService;


@Controller
public class PayrollController {

	@Autowired
	PayrollService payrollService;
	
	@GetMapping("/payroll/create")
	public String launchPlanning(Model model) {
		
		return "payroll/create";
		
	}
	
	@PostMapping("/payroll/create")
	public void storePlan(@RequestParam("schedule") String schedules,
						  @RequestParam("assignment") UUID assignment,
						  @RequestParam("type") String cycleType,
						  @RequestParam("duration") int cycleDuration) {
		
		payrollService.createPayroll(cycleDuration, schedules, assignment, cycleType);
		
	}
	
	@PostMapping("payroll/map")
	@ResponseBody
	public List<TimeListOutput> listTimesForPayroll(@RequestParam("startDay") LocalDate startDay,
													@RequestParam("endDay") LocalDate endDay,
													@RequestParam("userId") UUID userId) {
		
		return payrollService.listTimeWithinRange(startDay, endDay, userId);
		
	}
	
}
