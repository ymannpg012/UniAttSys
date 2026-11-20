package jp.co.uas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.uas.entities.Payroll;
import jp.co.uas.enums.DaysOfWeek;
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
	public Payroll createPayroll(String type, String schedules, int cycleDuration, boolean irregularWorkingHours, String shiftStartTime, String shiftEndTime, String workingDaysPreset, List<String> selectedWorkingDays,  UUID assignedTo) {
		
		Payroll p = new Payroll();
		
		TypesOfPayroll payrollType = inspectEnum(TypesOfPayroll.class, type);
		
		WorkDaysPreset workdaysPreset = inspectEnum(WorkDaysPreset.class, workingDaysPreset);
		
		Set<DaysOfWeek> days = selectedWorkingDays.stream().map(d -> inspectEnum(DaysOfWeek.class, d.trim().toUpperCase())).collect(Collectors.toCollection(() -> EnumSet.noneOf(DaysOfWeek.class)));
		
		p.setCycleType(payrollType.toString());
		p.setDaysOfShiftPreset(workdaysPreset.toString());
		
		if(cycleDuration != 0 && cycleDuration < 25 || cycleDuration > 0) {
			p.setCycleDuration(cycleDuration);
		}else {
			throw new RuntimeException("Invalid duration");
		}
		
		if(irregularWorkingHours) {
			p.setIrregular_hours(true);
		}else if( shiftStartTime != null || shiftEndTime != null ){
			p.setIrregular_hours(false);
			p.setShiftStart(shiftStartTime);
			p.setShiftEnd(shiftEndTime);
		}else {
			throw new RuntimeException("Invalid shift hours parameters");
		}
		
		if(schedules != null) {
			p.setCycleDates(schedules);
		}else {
			throw new RuntimeException(" schedules can not be empty! ");
		}
			
		if(workdaysPreset == WorkDaysPreset.IRREGULAR) {
			p.setDaysOfShift(null);
		}else if(!days.isEmpty()) {
			p.setDaysOfShift(days.toString());
		}else {
			throw new RuntimeException("Invalid workday preset value");
		}
		
		if(roleRepo.existsById(assignedTo)) {
			p.setAssignedTo(assignedTo);
		}else {
			throw new RuntimeException("Invalid role assignment");
		}
		
		return createPayroll.save(p);
		
	}
	
	private <E extends Enum<E>>E inspectEnum(Class<E> cls, String value){
		
		try {
			return Enum.valueOf(cls, value.trim().toUpperCase());
		}catch(IllegalArgumentException e) {
			throw new RuntimeException("Invalid " + cls.getSimpleName() + ": " + value);
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
