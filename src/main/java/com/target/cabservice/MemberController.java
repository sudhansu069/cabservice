package com.target.cabservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.cabservice.dto.CabDTO;
import com.target.cabservice.dto.DropPointDTO;
import com.target.cabservice.dto.Location;
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
	 
	 
	 @RequestMapping(value = "/hello" ,method = RequestMethod.POST,consumes = "Application/json")
		public ResponseEntity<String> addDropPoints(@RequestBody String json) throws JsonProcessingException, IOException {
			
			  /* if(!cabService.validateCabInfo(cabDto)) {
				   
				   return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			   }
			   cabService.updateCabInfo(cabDto);*/
		 
		   JsonFactory factory = new JsonFactory();
	       ObjectMapper mapper = new ObjectMapper(factory);
	       JsonNode rootNode = mapper.readTree(json);  
	       
	       List<DropLocationDistanceValueDataPair> dataPairList = new ArrayList<>();
	       
	       Iterator<Map.Entry<String,JsonNode>> fieldsIterator = rootNode.fields();
	       while (fieldsIterator.hasNext()) {

	           Map.Entry<String,JsonNode> field = fieldsIterator.next();
	           System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue().asText());
	           
	          DropLocationDistanceValueDataPair pair = new DropLocationDistanceValueDataPair(field.getKey(),field.getValue().asText());
	          
	          dataPairList.add(pair);
	       }
	       
	       cabService.saveDropLocations(dataPairList);
		       
		   return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	 
	
	 
	 
	
}
