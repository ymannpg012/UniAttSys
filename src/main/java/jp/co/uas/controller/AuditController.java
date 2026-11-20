package jp.co.uas.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.uas.model.UserInformationObject;
import jp.co.uas.service.UserInformationService;

@Controller
public class AuditController {
	
	@Autowired
	UserInformationService userInfoService;
	
	@GetMapping("/auditTime")
	public String launchEmployeeTimeAudit(Model model) {
		
		return "audit/employeeTimeAudit";
		
	}
	
	@PostMapping("/fetchEmployeesByRoles")
	@ResponseBody
	public List<UserInformationObject> launchUserInformationService(@RequestParam("role") UUID role) {
		
		return userInfoService.fetchUserInfomation(role);
		
	}
	
}
