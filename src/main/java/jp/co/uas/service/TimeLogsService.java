package jp.co.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.uas.model.PayrollCycleObject;
import jp.co.uas.repository.PayrollRepository;

@Service
public class TimeLogsService {

	@Autowired
	PayrollRepository payrollRepo;
	
	public PayrollCycleObject fetchPayrollCycleData(String assignment) {
		
		PayrollCycleObject cycleObject= new  PayrollCycleObject(payrollRepo.findByAssignment(assignment));
		
		return cycleObject;
		
		//return payrollRepo.findByAssignCycleTo(assignment);
		
	}
	
	
}
