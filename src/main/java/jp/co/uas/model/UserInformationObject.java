package jp.co.uas.model;

import java.util.List;
import java.util.UUID;

import jp.co.uas.entities.UserInformation;

public class UserInformationObject {

	private String firstName;
	
	private String lastName;
	
	private String companyEmail;
	
	private UUID userId;
	
	private UUID employmentRole;
	
	private List<UserInformation> userInfo;
	
	public UserInformationObject(List<UserInformation> userInfo) {
		
		this.userInfo = userInfo;
		
	}
	
	public List<UserInformation> getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(List<UserInformation> userInfo) {
		this.userInfo = userInfo;
	}

	public UserInformationObject(UserInformation userInfo) {
		this.firstName = userInfo.getFirstName();
		this.lastName = userInfo.getLastName();
		this.companyEmail = userInfo.getCompanyEmail();
		this.userId = userInfo.getUserId();
		this.employmentRole = userInfo.getEmploymentRole();
	}
	
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
