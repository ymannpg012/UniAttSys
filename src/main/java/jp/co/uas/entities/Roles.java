package jp.co.uas.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employment_roles")
public class Roles {

	@Id
	private UUID role_id;
	
	@Column(name="role_name")
	private String role_name;

	public UUID getRoleId() {
		return role_id;
	}

	public void setRoleId(UUID role_id) {
		this.role_id = role_id;
	}

	public String getRoleName() {
		return role_name;
	}

	public void setRoleName(String role_name) {
		this.role_name = role_name;
	}
	
}
