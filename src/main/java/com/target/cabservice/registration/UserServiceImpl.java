package com.target.cabservice.registration;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.cabservice.CabUser;
import com.target.cabservice.UserDao;
import com.target.cabservice.dto.RegistrationDTO;
/**
 * 
 * @author sudhansu
 *
 */
@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	public void register(RegistrationDTO regDto) {
		
		userDao.save(regDto);
		
	}

	@Override
	public boolean validateRegistartion(RegistrationDTO regDto) {
		 
		 List<CabUser> allCabOptedUsers = userDao.getAllUsers();
		 
		 boolean isdropPointExistInSytem = false;
		 
		 for(CabUser cu : allCabOptedUsers) {
			 
			    if(cu.getDropPoint().equalsIgnoreCase(regDto.getDrop_point())) {
			    	
			    	isdropPointExistInSytem =  true;
			    	break;
			    }
		 }
		 
		 return isdropPointExistInSytem ;
		 
		 
	}

}
