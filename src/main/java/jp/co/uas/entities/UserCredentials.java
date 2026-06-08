package jp.co.uas.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_credentials")
public class UserCredentials {

	@Id
	@Column(name = "user_uuid")
	private UUID userUUID;
	
	private String username;
	
	private String user_password;

	public UUID getUserUUID() {
		return userUUID;
	}

	public void setUserUUID(UUID userUUID) {
		this.userUUID = userUUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return user_password;
	}

	public void setUserPassword(String user_password) {
		this.user_password = user_password;
	}
	
}
