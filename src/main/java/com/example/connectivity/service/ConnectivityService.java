package com.example.connectivity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.connectivity.domain.AddConnectivityRequest;
import com.example.connectivity.repository.ConnectivityRepository;

@Service
public class ConnectivityService {
	
	@Autowired
	private ConnectivityRepository connectivityRepository;

	public boolean addConnectivity(AddConnectivityRequest addConnectivityRequest) {
		return connectivityRepository.addConnectivity(addConnectivityRequest.getOrigin(), addConnectivityRequest.getDestination());
	}
	
	public boolean isConnected(String origin, String destination) {
		return connectivityRepository.isConnected(origin, destination);
	}
	
}
