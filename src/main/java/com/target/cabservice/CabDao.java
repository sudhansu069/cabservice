package com.target.cabservice;

import java.util.List;

import com.target.cabservice.dto.CabDTO;

public interface CabDao {
   
	public void save(CabDTO cabDto);
	
	public void update(CabDTO regDto);
	
	public void delete(int Id);
	
	public List<CabDTO> getAllCabs();
	
	 
}
