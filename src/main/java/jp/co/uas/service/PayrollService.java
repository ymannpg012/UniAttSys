package jp.co.uas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.uas.entities.Payroll;
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
	public Payroll createPayroll(int cycleDuration, String schedules, UUID assignedTo, String type) {
		
		Payroll payrollCreationFields = new Payroll();
		
		payrollCreationFields.setCycleDates(schedules);
		payrollCreationFields.setAssignedTo(assignedTo);
		payrollCreationFields.setCycleType(type);
		payrollCreationFields.setCycleDuration(cycleDuration);
		
		return createPayroll.save(payrollCreationFields);
		
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
		
		return timeListOutput;
	}
	
}
