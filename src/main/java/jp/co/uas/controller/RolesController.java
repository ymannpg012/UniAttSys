package jp.co.uas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.uas.entities.Roles;
import jp.co.uas.service.RolesService;

@Controller
public class RolesController {

	@Autowired
	RolesService roleService;
	
	@ResponseBody	
	@PostMapping("/roles")
	public List<Roles> launchFetchRoles(){
		
		return roleService.fetchRoles();
		
	}
	
}
