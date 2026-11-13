package jp.co.uas.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payroll_cycle")
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID cycleId;
	
	private String cycleType;
	
	private String cycleDates;
	
	private int cycleDuration;

	private boolean irregular_hours;
	
	private String shiftStart;
	
	private String shiftEnd;
	
	private String daysOfShiftPreset;
	
	private String daysOfShift;
	
	@Column(name="assign_to_employment_role")
	private UUID assignedTo;

	public UUID getCycleId() {
		return cycleId;
	}
	
	public void setCycleId(UUID cycleId) {
		this.cycleId = cycleId;
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
	
	public boolean isIrregular_hours() {
		return irregular_hours;
	}

	public void setIrregular_hours(boolean irregular_hours) {
		this.irregular_hours = irregular_hours;
	}

	public String getShiftStart() {
		return shiftStart;
	}

	public void setShiftStart(String shiftStart) {
		this.shiftStart = shiftStart;
	}

	public String getShiftEnd() {
		return shiftEnd;
	}

	public void setShiftEnd(String shiftEnd) {
		this.shiftEnd = shiftEnd;
	}

	public String getDaysOfShiftPreset() {
		return daysOfShiftPreset;
	}

	public void setDaysOfShiftPreset(String daysOfShiftPreset) {
		this.daysOfShiftPreset = daysOfShiftPreset;
	}

	public String getDaysOfShift() {
		return daysOfShift;
	}

	public void setDaysOfShift(String daysOfShift) {
		this.daysOfShift = daysOfShift;
	}

	public UUID getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UUID assignedTo) {
		this.assignedTo = assignedTo;
	}

}
