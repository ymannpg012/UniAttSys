package jp.co.uas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.uas.entities.Roles;
import jp.co.uas.repository.RoleRepository;

@Service
public class RolesService {

	@Autowired
	RoleRepository roleRepo;
	
	public List<Roles> fetchRoles(){
		
		return roleRepo.findAll();
		
	}
	
}
