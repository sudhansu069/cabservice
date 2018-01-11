package com.target.cabservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.cabservice.dto.CabDTO;
import com.target.cabservice.dto.Location;
import com.target.cabservice.dto.RouteDTO;
import com.target.cabservice.exceptions.CabNotAvailableException;

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
	public List<RouteDTO> getAllRoutes() throws CabNotAvailableException{

		List<RouteDTO> routes = new ArrayList<>();

		RouteDTO routeDto = null;
        Location srcLocation = userDao.getSrcLocation();
		List<CabDTO> allAvalableCabs = cabDao.getAllCabs();
		Map<String,List<Integer>> dropLocWithUsers = userDao.getDropLocationWithMembersMap();
		StringBuilder route = null;
		
		try {
			for(Map.Entry<String, List<Integer>> entry : dropLocWithUsers.entrySet()) {
				
				   route = new StringBuilder();
				   route.append(srcLocation.getDescription()).append(",");
				   
				   String dropLocation = entry.getKey();
				   List<Integer> userIds = dropLocWithUsers.get(dropLocation);
				   
				   CabDTO currentCab = findAvailbleCabs(allAvalableCabs,userIds);
				   
				    if(currentCab == null) throw new CabNotAvailableException("No Cab Available now for Drop Location- "+dropLocation);
				    
				    routeDto = new RouteDTO();
				    
				    routeDto.setCab_id(currentCab.getId());
				    
				    while(currentCab.getCapacity() >= 0) {
				    	
				    	
				    	currentCab.setCapacity(currentCab.getCapacity()-1);
				    }
				    
				    if(currentCab.getCapacity() <= 0) {
				    	
				    	for(int i = 0;i<allAvalableCabs.size();i++) {
				    		
				    		  if(currentCab.getId() == allAvalableCabs.get(i).getId()) {
				    			  
				    			  allAvalableCabs.remove(i);
				    		  }
				    	}
				    }
				    
				    route.append(dropLocation).append(",");
				    route.deleteCharAt(route.length()-1);
					
					routeDto.setRoute(new String(route));
					routeDto.setRoute_cost(currentCab.getCost());
					StringBuilder memberIdBuilder = new StringBuilder();
					for(Integer id : userIds) {
						
						memberIdBuilder.append(id).append(",");
						
					}
					memberIdBuilder.deleteCharAt(memberIdBuilder.length()-1);
					routeDto.setTeam_member_ids(new String(memberIdBuilder));
					routes.add(routeDto);
			}
		} catch (Exception e) {
			 
			e.printStackTrace();
		}

	 
		return routes;
	}

	private CabDTO findAvailbleCabs(List<CabDTO> allAvalableCabs, List<Integer> usersIds) {
		 
		    CabDTO foundCab = null; 
            for(CabDTO cab : allAvalableCabs) {
            	
            	if(cab.getCapacity() >= usersIds.size()) {
            		
            		foundCab = cab;
            		break;
            		
            	}
            }
            
            return foundCab;
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
