package com.target.cabservice;

import com.target.cabservice.dto.CabDTO;

public interface CabService {

	  public void updateCabInfo(CabDTO cabDto);
	  
	  public boolean validateCabInfo(CabDTO cabDto);

	  
}
