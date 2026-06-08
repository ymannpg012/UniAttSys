package jp.co.uas.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="time_out")
public class TimeOut {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID time_out_id;
	
	@OneToOne
	@JoinColumn(name="time_in_origin")
	private TimeIn time_in_origin;

	@Column(name="time_out")
	private OffsetDateTime time_out;

	public UUID getTimeOutId() {
		return time_out_id;
	}

	public void setTimeOutId(UUID time_out_id) {
		this.time_out_id = time_out_id;
	}

	public TimeIn getTimeInOrigin() {
		return time_in_origin;
	}

	public void setTimeInOrigin(TimeIn time_in_origin) {
		this.time_in_origin = time_in_origin;
	}

	public OffsetDateTime getTimeOut() {
		return time_out;
	}

	public void setTimeOut(OffsetDateTime time_out) {
		this.time_out = time_out;
	}
}
