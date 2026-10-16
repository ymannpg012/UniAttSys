package jp.co.uas.service.audit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.uas.model.RolesObject;
import jp.co.uas.repository.RoleRepository;

@Service
public class AuditEmployeeAttendanceService {
	
	@Autowired
	RoleRepository roleRepo;
	
	public RolesObject fetchRoles(){
		
		 RolesObject rolesObject = new RolesObject(roleRepo.findRoleNames());
		 
		 return rolesObject;
		
	}
	
}
