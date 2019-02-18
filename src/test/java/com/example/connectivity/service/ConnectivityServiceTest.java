package com.example.connectivity.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.connectivity.repository.ConnectivityRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ConnectivityService.class)
public class ConnectivityServiceTest {

	@MockBean
	private ConnectivityRepository connectivityRepository;
	
	@Test
	public void testAddConnectivity_Success() {
		Mockito.when(connectivityRepository.addConnectivity(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.TRUE);
		assertEquals(connectivityRepository.addConnectivity("Trenton", "Newark"), Boolean.TRUE);
	}

	@Test
	public void testAddConnectivity_Fail() {
		assertEquals(connectivityRepository.addConnectivity("Trenton", "Albany"), Boolean.FALSE);
	}

	@Test
	public void testIsConnected_Success() {
		Mockito.when(connectivityRepository.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(Boolean.TRUE);
		assertEquals(connectivityRepository.isConnected("Boston", "New York"), Boolean.TRUE);
	}

	@Test
	public void testIsConnected_Fail() {
		assertEquals(connectivityRepository.isConnected("Boston", "Albany"), Boolean.FALSE);
	}

}
