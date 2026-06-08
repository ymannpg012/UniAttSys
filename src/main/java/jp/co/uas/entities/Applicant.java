package jp.co.uas.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="test_table")
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String test_username;
	
	
	//getters and setters
	
	public UUID getID() {
		return id;
	}
	
	public String getTestUsername() {
		return test_username;
	}
	
	public void setTestUsername(String test_username) {
		this.test_username = test_username;
	}
	
}
