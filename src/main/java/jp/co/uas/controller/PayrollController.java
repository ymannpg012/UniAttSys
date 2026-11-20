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
	public void storePlan(@RequestParam("type") String cycleType,
						  @RequestParam("schedule") String schedules,
						  @RequestParam("duration") int cycleDuration,
						  @RequestParam("irregularWorkingHours") boolean irregularWorkingHours,
						  @RequestParam("workingHoursStart") String shiftStartTime,
						  @RequestParam("workingHoursEnd") String shiftEndTime,
						  @RequestParam("workingDays") String workingDaysPreset,
						  @RequestParam(value = "selectedDays", defaultValue = "") List<String> selectedWorkingDays,
						  @RequestParam("assignment") UUID assignment) {
		

		System.out.println("irregularWorkingHours" + irregularWorkingHours);
		System.out.println("shiftStartTime" + shiftStartTime);
		System.out.println("shiftEndTime" + shiftEndTime);
		System.out.println("workingDaysPreset" + workingDaysPreset);
		System.out.println("selectedWorkingDays" + selectedWorkingDays);
		
		payrollService.createPayroll(cycleType, schedules, cycleDuration, irregularWorkingHours, shiftStartTime, shiftEndTime, workingDaysPreset, selectedWorkingDays, assignment);
		
	}
	
	@PostMapping("payroll/map")
	@ResponseBody
	public List<TimeListOutput> listTimesForPayroll(@RequestParam("startDay") LocalDate startDay,
													@RequestParam("endDay") LocalDate endDay,
													@RequestParam("userId") UUID userId) {
		
		return payrollService.listTimeWithinRange(startDay, endDay, userId);
		
	}
	
}
