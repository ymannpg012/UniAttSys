package jp.co.uas.model;


import java.util.UUID;


public class TimeOutObject {
	
	private String latestTimeOutDate;
	
	private String latestTimeOutTime;

	public String getLatestTimeOutDate() {
		return latestTimeOutDate;
	}

	public void setLatestTimeOutDate(String latestTimeOutDate) {
		this.latestTimeOutDate = latestTimeOutDate;
	}

	public String getLatestTimeOutTime() {
		return latestTimeOutTime;
	}

	public void setLatestTimeOutTime(String latestTimeOutTime) {
		this.latestTimeOutTime = latestTimeOutTime;
	}

	private UUID timeInOrigin;
	
	public UUID setTimeInOrigin(UUID timeInOrigin) {
		return this.timeInOrigin = timeInOrigin;
	}
	
	public UUID getTimeInOrigin() {
		return this.timeInOrigin;
	}
	
	@Override
	public String toString() {
		return "TimeOutObject [latestTimeOutDate=" + latestTimeOutDate + ", latestTimeOutTime=" + latestTimeOutTime
				+ ", timeInOrigin=" + timeInOrigin + "]";
	}
	
}
