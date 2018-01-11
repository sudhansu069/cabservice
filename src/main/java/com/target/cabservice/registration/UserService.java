package com.target.cabservice.registration;

import com.target.cabservice.dto.RegistrationDTO;
import com.target.cabservice.exceptions.InvalidDropLocationException;
/**
 * 
 * @author sudhansu
 *
 */
public interface UserService {
   
	
	   public void register(RegistrationDTO regDto);
	   
	   public void validateRegistartion(RegistrationDTO regDto) throws InvalidDropLocationException;
}
