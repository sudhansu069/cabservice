package com.target.cabservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.cabservice.dto.CabDTO;
import com.target.cabservice.dto.Location;
import com.target.cabservice.dto.RouteDTO;

@Service
public class CabServiceImpl implements CabService {
	
	@Autowired
	private CabDao cabDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public void addCab(CabDTO cabDto) {
		
		cabDao.save(cabDto);

	}

	@Override
	public boolean validateCabInfo(CabDTO cabDto) {

		return true;
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

	@Override
	public List<RouteDTO> getAllRoutes() {

		List<RouteDTO> routes = new ArrayList<>();

		RouteDTO routeDto = null;

		List<CabDTO> allAvalableCabs = cabDao.getAllCabs();
		List<Location> dropLocations = userDao.getAllDropLocations();

		List<Location> route = new ArrayList<>();
		route.add(dropLocations.get(0));

		int index = 1;

		for (CabDTO Cab : allAvalableCabs) {

			routeDto = new RouteDTO();
			routeDto.setCab_id(Cab.getCab_id());
			routeDto.setRoute(route);
			routeDto.setRoute_cost(Cab.getCost());

			if (index >= dropLocations.size())
				throw new RuntimeException("No Cabs Available");

			route.add(dropLocations.get(index++));
			routes.add(routeDto);

		}
		return routes;
	}

	@Override
	public double getTotalCost(List<RouteDTO> routes) {
		 
		double totalCost = 0.0;
		
		for(RouteDTO rtDto:routes) {
			
			totalCost += rtDto.getRoute_cost();
		}
		
		return totalCost;
	}

}
