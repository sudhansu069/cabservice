package com.target.cabservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.cabservice.dto.CabDTO;
import com.target.cabservice.dto.Location;

@Service
public class CabServiceImpl implements CabService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void updateCabInfo(CabDTO cabDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateCabInfo(CabDTO cabDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveDropLocations(List<DropLocationDistanceValueDataPair> dropLocationsList) {
		 
		  
		List<Location> tempList = new ArrayList<>();
		
		
		 
		 for(DropLocationDistanceValueDataPair pair : dropLocationsList) {
			 
			  Location loc = new Location(pair.getDropLoacation()) ;
			  tempList.add(loc);
		 }
		 
		 userDao.saveUserDropLocation(tempList);
		 userDao.saveDistance(dropLocationsList);
	}

}
