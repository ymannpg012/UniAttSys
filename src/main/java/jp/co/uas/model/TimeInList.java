package jp.co.uas.model;

import java.time.Instant;
import java.util.UUID;

public class TimeInList {

	private UUID timeInID;
	
	private Instant timeIn;
	
	public TimeInList(Instant timeIn, UUID timeInID) {
		this.timeInID = timeInID;
		this.timeIn = timeIn;
	}
	
	public UUID getTimeInID() {
		return timeInID;
	}

	public void setTimeInID(UUID timeInID) {
		this.timeInID = timeInID;
	}

	public Instant getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Instant timeIn) {
		this.timeIn = timeIn;
	}
	
	@Override
	public String toString() {
		return "TimeInList [timeInID=" + timeInID + ", timeIn=" + timeIn + "]";
	}
}
