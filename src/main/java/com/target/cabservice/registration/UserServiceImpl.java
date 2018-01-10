package com.target.cabservice.registration;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.cabservice.CabUser;
import com.target.cabservice.UserDao;
import com.target.cabservice.dto.Location;
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
		 
		 List<Location> allDropLocations = userDao.getAllDropLocations();
		 
		 boolean isdropPointExistInSytem = false;
		 
		 for(Location cu : allDropLocations) {
			 
			    if(cu.getDescription().equalsIgnoreCase(regDto.getDrop_point())) {
			    	
			    	isdropPointExistInSytem =  true;
			    	break;
			    }
		 }
		 
		 return isdropPointExistInSytem ;
		 
		 
	}

}
