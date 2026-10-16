package jp.co.uas.model;

import java.time.Instant;
import java.util.UUID;

public class TimeOutList {

	private UUID timeOutId;
	
	private UUID timeInOrigin;
	
	private Instant timeOut;
	
	public TimeOutList(UUID timeOutId, Instant timeOut, UUID timeInOrigin) {
		
		this.timeInOrigin = timeInOrigin;
		this.timeOut = timeOut;
		this.timeOutId = timeOutId;
		
	}
	
	public UUID getTimeOutId() {
		return timeOutId;
	}

	public void setTimeOutId(UUID timeOutId) {
		this.timeOutId = timeOutId;
	}

	public UUID getTimeInOrigin() {
		return timeInOrigin;
	}

	public void setTimeInOrigin(UUID timeInOrigin) {
		this.timeInOrigin = timeInOrigin;
	}

	public Instant getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Instant timeOut) {
		this.timeOut = timeOut;
	}
	
	@Override
	public String toString() {
		return "TimeOutList [timeInOrigin=" + timeInOrigin + ", timeOut=" + timeOut + "]";
	}
	
}
