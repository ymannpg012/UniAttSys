package jp.co.uas.model;

import java.time.Instant;
import java.util.UUID;

public class TimeListObject {

	private UUID userId;
	
	private UUID timeInId;
	
	private Instant timeInTime;
	
	private Instant timeOutTime;
	
	public TimeListObject(UUID userId, UUID timeInId, Instant timeInTime, Instant timeOutTime) {
		
		this.userId = userId;
		this.timeInId = timeInId;
		this.timeInTime = timeInTime;
		this.timeOutTime = timeOutTime;
		
	}
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getTimeInId() {
		return timeInId;
	}

	public void setTimeInId(UUID timeInId) {
		this.timeInId = timeInId;
	}
	public Instant getTimeInTime() {
		return timeInTime;
	}

	public void setTimeInTime(Instant timeInTime) {
		this.timeInTime = timeInTime;
	}

	public Instant getTimeOutTime() {
		return timeOutTime;
	}

	public void setTimeOutTime(Instant timeOutTime) {
		this.timeOutTime = timeOutTime;
	}
	@Override
	public String toString() {
		return "TimeListObject [timeInId=" + timeInId + ", timeInTime=" + timeInTime + ",  timeOutTime=" + timeOutTime +"]"+ "\n";
	}
	
}
