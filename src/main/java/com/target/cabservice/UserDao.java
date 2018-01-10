package com.target.cabservice;

import java.util.List;

import com.target.cabservice.dto.Location;
import com.target.cabservice.dto.RegistrationDTO;
/**
 * 
 * @author sudhansu
 *
 */
public interface UserDao {

	public void save(RegistrationDTO regDto);
	
	public void update(RegistrationDTO regDto);
	
	public void delete(int id);
	
	public List<CabUser> getAllUsers();
	
	public List<Location> getAllDropLocations();
	
	public void saveUserDropLocation(List<Location> dropLocationList);
	
	public void saveDistance(List<DropLocationDistanceValueDataPair> dropLocationsList);
	
	
}
