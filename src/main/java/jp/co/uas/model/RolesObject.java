package jp.co.uas.model;

import java.util.List;
import java.util.UUID;


import jp.co.uas.entities.Roles;

public class RolesObject {

	private UUID role_id;
	
	private String role_name;
	
	private List<Roles> roles;

    public RolesObject(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
    
    //for single roles
	public RolesObject(Roles roles) {
		this.role_id = roles.getRoleId();
		this.role_name = roles.getRoleName();
	}
	
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
