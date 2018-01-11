package com.target.cabservice;

import java.util.List;

import com.target.cabservice.dto.CabDTO;
import com.target.cabservice.dto.RouteDTO;
import com.target.cabservice.exceptions.CabNotAvailableException;

public interface CabService {

	  public void addCab(CabDTO cabDto);
	  
	  public boolean validateCabInfo(CabDTO cabDto);
	  
	  public void  saveDropLocations(List<DropLocationDistanceValueDataPair> dropLocationsList);

	public List<RouteDTO> getAllRoutes()throws CabNotAvailableException;

	public double getTotalCost(List<RouteDTO> routes);

	  
}
