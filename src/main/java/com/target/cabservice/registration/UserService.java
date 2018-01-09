package com.target.cabservice.registration;

import com.target.cabservice.dto.RegistrationDTO;
/**
 * 
 * @author sudhansu
 *
 */
public interface UserService {
   
	
	   public void register(RegistrationDTO regDto);
	   
	   public boolean validateRegistartion(RegistrationDTO regDto);
}
