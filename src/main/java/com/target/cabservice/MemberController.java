package com.target.cabservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.cabservice.dto.CabDTO;
import com.target.cabservice.dto.DropPointDTO;
import com.target.cabservice.dto.RegistrationDTO;
import com.target.cabservice.registration.UserService;
/**
 * 
 * @author sudhansu
 *
 */

@RestController
public class MemberController {
	
	@Autowired
	private UserService  registrationService;
	
	@Autowired
	private CabService cabService;

	 @RequestMapping(value = "/register" ,method = RequestMethod.POST,consumes = "Application/json")
	public ResponseEntity<String> register(@RequestBody List<RegistrationDTO> regDtoList) {
		 
		for (RegistrationDTO regDto : regDtoList) {

			if (!registrationService.validateRegistartion(regDto)) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			registrationService.register(regDto);

		}
		
		 
		  return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	 
	 
	 @RequestMapping(value = "/cabs" ,method = RequestMethod.POST,consumes = "Application/json")
	public ResponseEntity<String> postCab(@RequestBody List<CabDTO> cabDtoList) {

		for (CabDTO cabDto : cabDtoList) {

			if (!cabService.validateCabInfo(cabDto)) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			cabService.updateCabInfo(cabDto);
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	 
	 
	 @RequestMapping(value = "/dropPoints " ,method = RequestMethod.POST,consumes = "Application/json")
		public ResponseEntity<String> addDropPoints(@RequestBody DropPointDTO drpDto) {
			
			  /* if(!cabService.validateCabInfo(cabDto)) {
				   
				   return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			   }
			   cabService.updateCabInfo(cabDto);*/
			  return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	 
	
	 
	 
	
}
