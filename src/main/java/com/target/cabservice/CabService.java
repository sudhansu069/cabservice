package com.target.cabservice;

import java.util.List;

import com.target.cabservice.dto.CabDTO;

public interface CabService {

	  public void updateCabInfo(CabDTO cabDto);
	  
	  public boolean validateCabInfo(CabDTO cabDto);
	  
	  public void  saveDropLocations(List<DropLocationDistanceValueDataPair> dropLocationsList);

	  
}
