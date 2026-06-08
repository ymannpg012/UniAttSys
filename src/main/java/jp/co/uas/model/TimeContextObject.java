package jp.co.uas.model;

public class TimeContextObject {

	private boolean hasTimeInToday;
	
	private String LatestTimeInDate;
	
	private String LatestTimeInTime;
	
	private boolean hasTimeOutToday;
	
	private String LatestTimeOutDate;
	
	private String LatestTimeOutTime;

	public boolean hasTimeInToday() {
		return hasTimeInToday;
	}

	public void setHasTimeInToday(boolean hasTimeInToday) {
		this.hasTimeInToday = hasTimeInToday;
	}
	
	public boolean getHasTimeInToday() {
		return hasTimeInToday;
	}

	public String getLatestTimeInDate() {
		return LatestTimeInDate;
	}

	public void setLatestTimeInDate(String latestTimeInDate) {
		LatestTimeInDate = latestTimeInDate;
	}

	public String getLatestTimeInTime() {
		return LatestTimeInTime;
	}

	public void setLatestTimeInTime(String latestTimeInTime) {
		LatestTimeInTime = latestTimeInTime;
	}

	public boolean hasTimeOutToday() {
		return hasTimeOutToday;
	}

	public void setHasTimeOutToday(boolean hasTimeOutToday) {
		this.hasTimeOutToday = hasTimeOutToday;
	}
	
	public boolean getHasTimeOutToday() {
		return hasTimeOutToday;
	}

	public String getLatestTimeOutDate() {
		return LatestTimeOutDate;
	}

	public void setLatestTimeOutDate(String latestTimeOutDate) {
		LatestTimeOutDate = latestTimeOutDate;
	}

	public String getLatestTimeOutTime() {
		return LatestTimeOutTime;
	}

	public void setLatestTimeOutTime(String latestTimeOutTime) {
		LatestTimeOutTime = latestTimeOutTime;
	}

	

}
