package com.target.cabservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.cabservice.dto.CabDTO;
import com.target.cabservice.dto.CabRequestWrapper;
import com.target.cabservice.dto.RegistrationDTO;
import com.target.cabservice.dto.RegistrationDTOWrapper;
import com.target.cabservice.dto.RouteDTO;
import com.target.cabservice.dto.RoutePlanResponse;
import com.target.cabservice.exceptions.CabNotAvailableException;
import com.target.cabservice.exceptions.InsufficientCabException;
import com.target.cabservice.exceptions.InvalidDropLocationException;
import com.target.cabservice.registration.UserService;
/**
 * The MemberController.class
 * @author sudhansu
 *
 */

@RestController
public class MemberController {

	@Autowired
	private UserService registrationService;

	@Autowired
	private CabService cabService;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "Application/json")
	public ResponseEntity<String> register(@RequestBody RegistrationDTOWrapper regDtoListWrapper) {

		ResponseEntity<String> regResponse = null;

		try {

			List<RegistrationDTO> regDtoList = regDtoListWrapper.getTeam_members();

			for (RegistrationDTO regDto : regDtoList) {

				registrationService.validateRegistartion(regDto);
				registrationService.register(regDto);
			}
			regResponse = ResponseEntity.status(HttpStatus.CREATED).build();

		} catch (InvalidDropLocationException e) {

			e.printStackTrace();
			regResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

		} catch (Exception ex) {
			ex.printStackTrace();
			regResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

		return regResponse;

	}

	@RequestMapping(value = "/cabs", method = RequestMethod.POST, consumes = "Application/json")
	public ResponseEntity<String> postCab(@RequestBody CabRequestWrapper cabRequestWrapper) {

		List<CabDTO> cabDtoList = cabRequestWrapper.getCabs();
		ResponseEntity<String> cabPostingResponse = null;

		try {

			cabService.validate(cabDtoList);

			for (CabDTO cabDto : cabDtoList) {

				cabService.addCab(cabDto);
			}
			cabPostingResponse = ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (InsufficientCabException e) {

			e.printStackTrace();
			cabPostingResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (Exception e1) {

			e1.printStackTrace();
			cabPostingResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return cabPostingResponse;
	}

	@RequestMapping(value = "/drop_points", method = RequestMethod.POST, consumes = "Application/json")
	public ResponseEntity<String> addDropPoints(@RequestBody String json) throws JsonProcessingException, IOException {

		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(json);

		List<DropLocationDistanceValueDataPair> dataPairList = new ArrayList<>();

		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
		while (fieldsIterator.hasNext()) {

			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue().asText());

			DropLocationDistanceValueDataPair pair = new DropLocationDistanceValueDataPair(field.getKey(),
					field.getValue().asText());

			dataPairList.add(pair);
		}

		cabService.saveDropLocations(dataPairList);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@RequestMapping(value = "/route_plan", method = RequestMethod.GET, produces = "Application/json")
	public @ResponseBody RoutePlanResponse getRoutePlans() {

		RoutePlanResponse response = new RoutePlanResponse();

		List<RouteDTO> routes = new ArrayList<>();

		try {

			routes.addAll(cabService.getAllRoutes());

			double totalCost = cabService.getTotalCost(routes);

			response.setRoutes(routes);
			response.setTotal_cost(totalCost);
		} catch (CabNotAvailableException e) {

			e.printStackTrace();
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return response;

	}

}
