package jp.co.uas.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="time_in")
public class TimeIn {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "time_in_id")
	private UUID timeInID;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserCredentials user;
	
	@Column(name = "time_in")
	private OffsetDateTime timeIn;

	public UUID getTimeInID() {
		return timeInID;
	}

	public void setTimeInID(UUID timeInID) {
		this.timeInID = timeInID;
	}

	public UserCredentials getUser() {
		return user;
	}

	public void setUser(UserCredentials user) {
		this.user = user;
	}

	public OffsetDateTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(OffsetDateTime timeIn) {
		this.timeIn = timeIn;
	}
	
	 @Override
	    public String toString() {
			return "time in: " + timeIn;
	 }
	 
}
