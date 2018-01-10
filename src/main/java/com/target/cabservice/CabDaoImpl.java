package com.target.cabservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.target.cabservice.dto.CabDTO;

@Component
public class CabDaoImpl implements CabDao {
	
	List<CabDTO> cabs = new ArrayList<>();

	@Override
	public void save(CabDTO cabDto) {
		 
          cabs.add(cabDto);		

	}

	@Override
	public void update(CabDTO regDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int Id) {
		 
		  Iterator<CabDTO> itr = cabs.iterator();
		  
		  while(itr.hasNext()) {
			  
			    CabDTO cab = itr.next();
			    if(cab.getCab_id() == Id) {
			    	
			    	itr.remove();
			    	break;
			    }
		  }

	}

	@Override
	public List<CabDTO> getAllCabs() {
		 
		return cabs;
	}

}
