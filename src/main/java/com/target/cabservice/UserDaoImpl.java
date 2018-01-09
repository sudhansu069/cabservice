package com.target.cabservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.target.cabservice.dto.RegistrationDTO;
/**
 * Using In memory DB to save User Details
 * @author sudhansu
 *
 */
@Component
public class UserDaoImpl implements UserDao {
	
	private List<CabUser> cabserviceOptedUsers = new ArrayList<>();

	
	
	public List<CabUser> getAllUsers() {
		
		return cabserviceOptedUsers;
	}
	
	

	@Override
	public void save(RegistrationDTO regDto) {
		 
		cabserviceOptedUsers.add(new CabUser(regDto.getTeam_member_id(), regDto.getGender(), regDto.getDrop_point()));

	}

	@Override
	public void update(RegistrationDTO regDto) {
		
		CabUser existingUser = getUserById(regDto.getTeam_member_id());
		
		existingUser.setDropPoint(regDto.getDrop_point());
		existingUser.setGender(regDto.getGender());
        
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}
	
	private CabUser getUserById(int id) {
		
		CabUser existingUser = null;
        for(CabUser user : cabserviceOptedUsers) {
        	
        	  if(user.getId() == id ) {
        		  
        		  existingUser = user;
        		  break;
        	  }
        }
        
        return existingUser;
	}

}