package jp.co.uas.model;

import java.util.UUID;

import jp.co.uas.entities.Payroll;

public class PayrollCycleObject {

	private UUID payrollId;
	
	private String cycleType;
	
	private String cycleDates;
	
	private int cycleDuration;
	
	private boolean irregularWorkingHours;
	
	private String workHourStart;
	
	private String workHourEnd;
	
	private String workDaysPreset;
	
	private String selectedWorkDays;
	
	private UUID assignCycleTo;
	
	public PayrollCycleObject(Payroll payroll) {
		this.payrollId = payroll.getCycleId();
		this.cycleType = payroll.getCycleType();
		this.cycleDates = payroll.getCycleDates();
		this.cycleDuration = payroll.getCycleDuration();
		this.irregularWorkingHours = payroll.isIrregular_hours();
		this.workHourStart = payroll.getShiftStart();
		this.workHourEnd = payroll.getShiftEnd();
		this.workDaysPreset = payroll.getDaysOfShiftPreset();
		this.selectedWorkDays = payroll.getDaysOfShift();
		this.assignCycleTo = payroll.getAssignedTo();
	}

	public UUID getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(UUID payrollId) {
		this.payrollId = payrollId;
	}
	
	public String getCycleType() {
		return cycleType;
	}

	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}

	public String getCycleDates() {
		return cycleDates;
	}

	public void setCycleDates(String cycleDates) {
		this.cycleDates = cycleDates;
	}
	
	public int getCycleDuration() {
		return cycleDuration;
	}

	public void setCycleDuration(int cycleDuration) {
		this.cycleDuration = cycleDuration;
	}

	public boolean isIrregularWorkingHours() {
		return irregularWorkingHours;
	}

	public void setIrregularWorkingHours(boolean irregularWorkingHours) {
		this.irregularWorkingHours = irregularWorkingHours;
	}

	public String getWorkHourStart() {
		return workHourStart;
	}

	public void setWorkHourStart(String workHourStart) {
		this.workHourStart = workHourStart;
	}

	public String getWorkHourEnd() {
		return workHourEnd;
	}

	public void setWorkHourEnd(String workHourEnd) {
		this.workHourEnd = workHourEnd;
	}

	public String getWorkDaysPreset() {
		return workDaysPreset;
	}

	public void setWorkDaysPreset(String workDaysPreset) {
		this.workDaysPreset = workDaysPreset;
	}

	public String getSelectedWorkDays() {
		return selectedWorkDays;
	}

	public void setSelectedWorkDays(String selectedWorkDays) {
		this.selectedWorkDays = selectedWorkDays;
	}

	public UUID getAssignCycleTo() {
		return assignCycleTo;
	}

	public void setAssignCycleTo(UUID assignCycleTo) {
		this.assignCycleTo = assignCycleTo;
	}

}
