package jp.co.uas.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.uas.model.UserInformationObject;
import jp.co.uas.repository.UserInformationRepository;

@Service
public class UserInformationService {

	@Autowired
	UserInformationRepository userInfoRepo;
	
	public UserInformationObject fetchUserInfomation(UUID employmentRole){
		
		return new UserInformationObject(userInfoRepo.findByEmploymentRole(employmentRole));
		
	}
	
}
