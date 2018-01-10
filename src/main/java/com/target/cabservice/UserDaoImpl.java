package com.target.cabservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.target.cabservice.dto.Location;
import com.target.cabservice.dto.RegistrationDTO;
/**
 * Using In memory DB to save User Details
 * @author sudhansu
 *
 */
@Component
public class UserDaoImpl implements UserDao {
	
	private List<CabUser> cabserviceOptedUsers = new ArrayList<>();
	private List<Location> dropLocation = new ArrayList<>();
	private Map<Location,List<Integer>> dropLocationToIdsMap = new HashMap<>();
	
	private int[][] srcToDestDistance;
	 

	
	
	public List<CabUser> getAllUsers() {
		
		return cabserviceOptedUsers;
	}
	
	public List<Location> getAllDropLocations(){
		
		List<Location> dropLocations = new ArrayList<>();
		
		// Filter out the Drop Location Excluding the Pickup Location
		if(dropLocation.size() >1) {
			
			for(int i = 1;i<dropLocation.size();i++) {
				
				dropLocations.add(dropLocation.get(i));
			}
		}
		
		return dropLocations;
	}
	
	

	@Override
	public void save(RegistrationDTO regDto) {
		 
		cabserviceOptedUsers.add(new CabUser(regDto.getTeam_member_id(), regDto.getGender(), regDto.getDrop_point()));
		
		if(dropLocationToIdsMap.get(regDto.getDrop_point()) == null) {
			
			dropLocationToIdsMap.put(new Location(regDto.getDrop_point()), new ArrayList<>());
		}
		dropLocationToIdsMap.get(new Location(regDto.getDrop_point())).add(regDto.getTeam_member_id());

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



	@Override
	public void saveUserDropLocation(List<Location> dropLocationList) {
		   
		    
		  for(Location loc : dropLocationList) {
			  
			  dropLocation.add(loc);
		  }
	}



	@Override
	public void saveDistance(List<DropLocationDistanceValueDataPair> dropLocationsList) {
	     
		   
		srcToDestDistance  = new int[dropLocationsList.size()][dropLocationsList.size()];
		
		for(int i = 0;i<dropLocationsList.size();i++) {
			
			 String[] distance = dropLocationsList.get(i).getDistance().split(",");
			
			  for(int j =0;j<distance.length;j++) {
				  
				  srcToDestDistance[i][j] = Integer.parseInt((distance[j]));
			  }
		}
		     
		
	}

}
