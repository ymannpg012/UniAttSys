package jp.co.uas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.uas.repository.ApplicantRepository;
import jp.co.uas.entities.UserCredentials;
import jp.co.uas.model.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	ApplicantRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserCredentials user = repo.findByUsername(username);
		
		return new UserPrincipal(user);
		
	}
	
}
