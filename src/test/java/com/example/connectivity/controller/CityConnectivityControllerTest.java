package com.example.connectivity.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.connectivity.constant.CityConnectivityConstant;
import com.example.connectivity.domain.AddConnectivityRequest;
import com.example.connectivity.service.ConnectivityService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CityConnectivityController.class)
public class CityConnectivityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConnectivityService connectivityService;
	
	@Test
	public void addConnectivity_Success() throws Exception {
		
		Mockito.when(connectivityService.addConnectivity(Mockito.any(AddConnectivityRequest.class))).thenReturn(Boolean.TRUE);

		String addConnectivityJason = "{\"origin\": \"Trenton\", \"destination\": \"Newark\"}";

		mockMvc.perform(post("/addConnectivity").contentType(MediaType.APPLICATION_JSON).content(addConnectivityJason)).andExpect(status().isCreated()).andExpect(content().string(CityConnectivityConstant.CONNECTIVITY_ADDED_MSG));
	}
	
	@Test
	public void addConnectivity_UnableToAdd() throws Exception {
		
		Mockito.when(connectivityService.addConnectivity(Mockito.any(AddConnectivityRequest.class))).thenReturn(Boolean.FALSE);
		
		String addConnectivityJason = "{\"origin\": \"Trenton\", \"destination\": \"Newark\"}";
		
		mockMvc.perform(post("/addConnectivity").contentType(MediaType.APPLICATION_JSON).content(addConnectivityJason)).andExpect(status().isOk()).andExpect(content().string(CityConnectivityConstant.CONNECTIVITY__NOT_ADDED_MSG));
	}
	
	@Test
	public void addConnectivity_BlankOrigin() throws Exception {
		
		String addConnectivityJason = "{\"origin\": \" \", \"destination\": \"Newark\"}";
		
		mockMvc.perform(post("/addConnectivity").contentType(MediaType.APPLICATION_JSON).content(addConnectivityJason)).andExpect(status().isNotAcceptable()).andExpect(content().string(CityConnectivityConstant.CITY_EMPTY_ERR_MSG));
	}
	
	@Test
	public void addConnectivity_SameCity() throws Exception {
		
		String addConnectivityJason = "{\"origin\": \"Trenton\", \"destination\": \"Trenton\"}";
		
		mockMvc.perform(post("/addConnectivity").contentType(MediaType.APPLICATION_JSON).content(addConnectivityJason)).andExpect(status().isNotAcceptable()).andExpect(content().string(CityConnectivityConstant.CITY_SAME_ERR_MSG));
	}
	
	@Test
	public void checkConnectivity_Yes() throws Exception {
		Mockito.when(connectivityService.isConnected(Mockito.anyString(),Mockito.anyString())).thenReturn(Boolean.TRUE);
		
		mockMvc.perform(get("/connected").param("origin", "Newark").param("destination", "Boston")).andExpect(status().isOk()).andExpect(content().string(CityConnectivityConstant.CITY_CONNECTED_YES));
	}

	@Test
	public void checkConnectivity_No() throws Exception {
		Mockito.when(connectivityService.isConnected(Mockito.anyString(),Mockito.anyString())).thenReturn(Boolean.FALSE);
		
		mockMvc.perform(get("/connected").param("origin", "Newark").param("destination", "Albany")).andExpect(status().isOk()).andExpect(content().string(CityConnectivityConstant.CITY_CONNECTED_NO));
	}

}
