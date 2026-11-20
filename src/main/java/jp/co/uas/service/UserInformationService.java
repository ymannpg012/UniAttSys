package jp.co.uas.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.uas.entities.UserInformation;
import jp.co.uas.model.UserInformationObject;
import jp.co.uas.repository.UserInformationRepository;

@Service
public class UserInformationService {

	@Autowired
	UserInformationRepository userInfoRepo;
	
	public List<UserInformationObject> fetchUserInfomation(UUID employmentRole){
		
		List<UserInformation> listOfUsersInfo = userInfoRepo.findByEmploymentRole(employmentRole);
		
		List<UserInformationObject> userInfo = listOfUsersInfo.stream().map(u ->new UserInformationObject(u)).toList();
		
		return userInfo;
		
	}
	
}
