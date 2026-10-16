package jp.co.uas.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_information")
public class UserInformation {
	
	@Column(name="user_firstname")
	private String firstName;
	
	@Column(name="user_lastname")
	private String lastName;
	
	@Column(name="user_company_email")
	private String companyEmail;
	
	@Id
	@Column(name="user_UUID")
	private UUID userId;
	
	@Column(name="employmentRole")
	private UUID employmentRole;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public UUID getEmploymentRole() {
		return employmentRole;
	}

	public void setEmploymentRole(UUID employmentRole) {
		this.employmentRole = employmentRole;
	}
	
}
