package jp.co.uas.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.uas.entities.UserCredentials;

public class UserPrincipal implements UserDetails{
	
	private UserCredentials cred;
	
	public UserPrincipal(UserCredentials cred) {
		
		this.cred = cred;
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public @Nullable String getPassword() {
		
		return cred.getUserPassword();
	}

	@Override
	public String getUsername() {
		
		return cred.getUsername();
	}

	public UUID getUserID() {
		return cred.getUserUUID();
	}
	
}
