package jp.co.uas.model;

import java.time.OffsetDateTime;

import jp.co.uas.entities.TimeIn;

public class TimeInObject {

	private String lastTimeInDate;
	
	private String lastTimeInTime;
	
	private TimeIn timeInID;
	
	private OffsetDateTime timeIn;
	
	public TimeInObject(OffsetDateTime timeIn) {
		this.timeIn = timeIn;
	}
	
	public String getLastTimeInDate() {
		return lastTimeInDate;
	}

	public void setLastTimeInDate(String lastTimeInDate) {
		this.lastTimeInDate = lastTimeInDate;
	}

	public String getLastTimeInTime() {
		return lastTimeInTime;
	}

	public void setLastTimeInTime(String lastTimeInTime) {
		this.lastTimeInTime = lastTimeInTime;
	}
	
	public TimeIn getTimeInID() {
		return timeInID;
	}

	public void setTimeInID( TimeIn timeInID) {
		this.timeInID = timeInID;
	}

	public OffsetDateTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(OffsetDateTime timeIn) {
		this.timeIn = timeIn;
	}
}
