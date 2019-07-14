package com.example.connectivity.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

@Repository
public class ConnectivityRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectivityRepository.class);

	//SelfLoop option is set to false, because city self connectivity is not required.
	private MutableGraph<String> cityGraph = GraphBuilder.undirected().allowsSelfLoops(false).build();
	
	@PostConstruct
	private void loadConnectedCities() {
		//Create initial graph based on given list of connected cities from the resource file city.txt
		Resource resource = new ClassPathResource("city.txt");
		try (Stream<String> stream = new BufferedReader(new InputStreamReader(resource.getInputStream())).lines()) {
		//try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource("city.txt").toURI()))) {
		//try (Stream<String> stream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("classpath:files/city.txt"))).lines()) {
	        stream.forEach(line -> {
	        	String[] cityArr = line.split(",");
	        	cityGraph.putEdge(cityArr[0].trim(), cityArr[1].trim());
	        });
	        
	        stream.close();
		} catch (Exception e) {
			LOGGER.error("Exception occurred while loading the connected cities from file :: " + e.getMessage());
			e.printStackTrace();
		}
		
		LOGGER.info("Successfully loaded initial list of connected cities");
	}
	
	public boolean addConnectivity(String origin, String destination) {
		LOGGER.info("Adding connectivity between two cities");

		//Adds node to the graph and create edge between given nodes.
		return cityGraph.putEdge(origin, destination);
	}
	
	public boolean isConnected(String origin, String destination) {
		LOGGER.info("Checking connectivity between two cities");
		
		boolean isConnected = false;
		
		//Finding the destination city in the adjacent nodes of origin or adjacent nodes of the origin's adjacent nodes
		if(cityGraph.nodes().contains(origin) && cityGraph.nodes().contains(destination)) {
			isConnected = cityGraph.adjacentNodes(origin).stream().anyMatch(node -> node.equalsIgnoreCase(destination) || cityGraph.adjacentNodes(node).contains(destination));
		}

		return isConnected;
	}
	
	/*
	 * public void getAllConnectivity() { ArrayList<String> list = new
	 * ArrayList<>();
	 * cityGraph.edges().stream().map(Object::toString).collect(list); }
	 */
}
