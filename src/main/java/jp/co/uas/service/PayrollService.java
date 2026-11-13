package jp.co.uas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.uas.entities.Payroll;
import jp.co.uas.enums.TypesOfPayroll;
import jp.co.uas.enums.WorkDaysPreset;
import jp.co.uas.model.TimeListObject;
import jp.co.uas.model.TimeListOutput;
import jp.co.uas.repository.PayrollRepository;
import jp.co.uas.repository.RoleRepository;
import jp.co.uas.repository.TimeInRepository;
import jp.co.uas.repository.TimeOutRepository;
import jp.co.uas.service.helper.CurrentUserService;

@Service
public class PayrollService {

	@Autowired
	CurrentUserService currentUserService;
	
	@Autowired
	TimeInRepository timeInRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UtilityService util;
	
	@Autowired
	TimeOutRepository timeOutRepo;
	
	private final PayrollRepository createPayroll;
	
	public PayrollService(PayrollRepository createPayroll) {
		this.createPayroll = createPayroll;
	}
	
	@Transactional
	public Payroll createPayroll(String type, String schedules, int cycleDuration, boolean irregularWorkingHours, String shiftStartTime, String shiftEndTime, String workingDaysPreset, String selectedWorkingDays,  UUID assignedTo) {
		
		try {
			
			Payroll payrollCreationFields = new Payroll();
			
			WorkDaysPreset irregularPreset = WorkDaysPreset.IRREGULAR;
			
			TypesOfPayroll.valueOf(type.toUpperCase());
			WorkDaysPreset.valueOf(workingDaysPreset.toUpperCase());
			
			if(schedules != null) {
				
				payrollCreationFields.setCycleDates(schedules);
				
			}else {
				
				throw new RuntimeException("Invalid schedule: " + schedules);
				
			}
			
			if(cycleDuration != 0 && cycleDuration <= 365) {
				
				payrollCreationFields.setCycleDuration(cycleDuration);
				
			}else {
				
				throw new RuntimeException("Invalid duration: " + schedules);
				
			}
			
			if(irregularWorkingHours) {
				
				payrollCreationFields.setIrregular_hours(irregularWorkingHours);
				payrollCreationFields.setShiftStart(null);
				payrollCreationFields.setShiftEnd(null);
				
			}else if(!irregularWorkingHours && shiftStartTime != null && irregularWorkingHours && shiftEndTime != null) {
				
				payrollCreationFields.setIrregular_hours(false);
				payrollCreationFields.setShiftStart(shiftStartTime);
				payrollCreationFields.setShiftEnd(shiftEndTime);
				
			}else {
				
				throw new RuntimeException("Invalid working hours: " + irregularWorkingHours + " " + shiftStartTime + " " + shiftEndTime);
				
			}
			
			if(irregularPreset.name().equalsIgnoreCase(workingDaysPreset) && selectedWorkingDays.equals("[]")) {
				
				payrollCreationFields.setDaysOfShiftPreset(workingDaysPreset);
				payrollCreationFields.setDaysOfShift(null);
				
			}else if(!selectedWorkingDays.equals("[]")) {
				
				payrollCreationFields.setDaysOfShiftPreset(workingDaysPreset);
				payrollCreationFields.setDaysOfShift(selectedWorkingDays);
				
			}else {
				
				throw new RuntimeException("Invalid working days selected: " + workingDaysPreset + " " + selectedWorkingDays);
				
			}
			
			if(roleRepo.existsById(assignedTo)) {
				
				payrollCreationFields.setAssignedTo(assignedTo);
				
			}else {
				
				throw new RuntimeException("Invalid payroll type: " + assignedTo);
				
			}
			
			payrollCreationFields.setCycleType(type);
			
			return createPayroll.save(payrollCreationFields);
			
		}catch(IllegalArgumentException e) {
			
			throw new RuntimeException("Invalid param: " + type + " " + workingDaysPreset);
			
		}
		
	}
	
	public List<TimeListOutput> listTimeWithinRange(LocalDate start, LocalDate end, UUID userId) {
		
		
		List<TimeListObject> timeList = timeInRepo.listTimeByRange(start, end, userId);
		List<TimeListOutput> timeListOutput = new ArrayList<>();
		
		for(TimeListObject time : timeList) {
			
			TimeListOutput timePair = new TimeListOutput();
			
			timePair.setDate(util.instantDateFormatter(time.getTimeInTime()));
			timePair.setTimeIn(util.instantTimeFormatter(time.getTimeInTime()));
			timePair.setTimeOut(util.instantTimeFormatter(time.getTimeOutTime()));
			
			timeListOutput.add(timePair);
			
		}
		
		//System.out.println(start);
		//System.out.println(end);
		//System.out.println(timeListOutput.toString());
		
		return timeListOutput;
	}
	
}
