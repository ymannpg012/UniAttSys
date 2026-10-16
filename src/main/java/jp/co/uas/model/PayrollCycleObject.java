package jp.co.uas.model;

import java.util.UUID;

import jp.co.uas.entities.Payroll;

public class PayrollCycleObject {

	private UUID payrollId;
	
	private String cycleDates;
	
	private UUID assignCycleTo;
	
	private int cycleDuration;
	
	private String cycleType;
	
	public PayrollCycleObject(Payroll payroll) {
		this.payrollId = payroll.getCycleId();
		this.cycleDates = payroll.getCycleDates();
		this.assignCycleTo = payroll.getAssignedTo();
		this.cycleType = payroll.getCycleType();
		this.cycleDuration = payroll.getCycleDuration();
	}

	public int getCycleDuration() {
		return cycleDuration;
	}

	public void setCycleDuration(int cycleDuration) {
		this.cycleDuration = cycleDuration;
	}

	public UUID getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(UUID payrollId) {
		this.payrollId = payrollId;
	}

	public String getCycleDates() {
		return cycleDates;
	}

	public void setCycleDates(String cycleDates) {
		this.cycleDates = cycleDates;
	}

	public UUID getAssignCycleTo() {
		return assignCycleTo;
	}

	public void setAssignCycleTo(UUID assignCycleTo) {
		this.assignCycleTo = assignCycleTo;
	}

	public String getCycleType() {
		return cycleType;
	}

	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}
	
}
