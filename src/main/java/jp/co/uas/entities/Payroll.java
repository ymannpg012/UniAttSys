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
	
	private String cycleDates;

	private String cycleType;
	
	private int cycleDuration;
	
	@Column(name="assign_to_employment_role")
	private UUID assignedTo;

	public int getCycleDuration() {
		return cycleDuration;
	}

	public void setCycleDuration(int cycleDuration) {
		this.cycleDuration = cycleDuration;
	}

	public UUID getCycleId() {
		return cycleId;
	}

	public UUID getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(UUID assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setCycleId(UUID cycleId) {
		this.cycleId = cycleId;
	}

	public String getCycleDates() {
		return cycleDates;
	}

	public void setCycleDates(String cycleDates) {
		this.cycleDates = cycleDates;
	}

	public String getCycleType() {
		return cycleType;
	}

	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}
	
	
}
