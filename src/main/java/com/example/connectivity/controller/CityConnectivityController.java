package com.example.connectivity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.connectivity.constant.CityConnectivityConstant;
import com.example.connectivity.domain.AddConnectivityRequest;
import com.example.connectivity.service.ConnectivityService;

@RestController()
public class CityConnectivityController {

	@Autowired
	private ConnectivityService connectivityService;
	
	@GetMapping(path="/connected",params={"origin","destination"})
	public String checkConnectivity(@RequestParam String origin, @RequestParam String destination) {
		return connectivityService.isConnected(origin, destination) ? CityConnectivityConstant.CITY_CONNECTED_YES : CityConnectivityConstant.CITY_CONNECTED_NO;
	}
	
	/*
	 * @GetMapping(path="/getAllConnected") public @ResponseBody City
	 */
	
	@PostMapping(path="/addConnectivity")
	public ResponseEntity<String> addConnectivity(@RequestBody AddConnectivityRequest addConnectivityRequest) {
		ResponseEntity<String> response;
		
		if(addConnectivityRequest == null || StringUtils.isEmpty(addConnectivityRequest.getOrigin().trim()) || StringUtils.isEmpty(addConnectivityRequest.getDestination().trim())) {
			response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(CityConnectivityConstant.CITY_EMPTY_ERR_MSG);
		} else if(addConnectivityRequest.getOrigin().trim().equalsIgnoreCase(addConnectivityRequest.getDestination().trim())) {
			response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(CityConnectivityConstant.CITY_SAME_ERR_MSG);
		} else {
			if(connectivityService.addConnectivity(addConnectivityRequest)) {
				response = ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri()).body(CityConnectivityConstant.CONNECTIVITY_ADDED_MSG);
			} else {
				response = ResponseEntity.ok(CityConnectivityConstant.CONNECTIVITY__NOT_ADDED_MSG);
			}
		}
		return response;
	}
}
