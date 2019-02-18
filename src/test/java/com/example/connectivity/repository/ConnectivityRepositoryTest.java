package com.example.connectivity.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class ConnectivityRepositoryTest {

	MutableGraph<String> cityGraph = GraphBuilder.undirected().allowsSelfLoops(false).build();

	@Before
	public void initiateCityGraph() {
		cityGraph.putEdge("Boston", "New York");
		cityGraph.putEdge("Philadelphia", "Newark");
		cityGraph.putEdge("Newark", "Boston");
		cityGraph.putEdge("Trenton", "Albany");
	}
	
	@Test
	public void testAddConnectivity_Success() {
		assertEquals(cityGraph.putEdge("Trenton", "Newark"), Boolean.TRUE);
	}

	@Test
	public void testAddConnectivity_Fail() {
		assertEquals(cityGraph.putEdge("Trenton", "Albany"), Boolean.FALSE);
	}

	@Test
	public void testIsConnected_Success() {
		assertEquals(cityGraph.adjacentNodes("Boston").stream().anyMatch(node -> node.equalsIgnoreCase("New York") || cityGraph.adjacentNodes(node).contains("New York")), Boolean.TRUE);
	}

	@Test
	public void testIsConnected_Fail() {
		assertEquals(cityGraph.adjacentNodes("Boston").stream().anyMatch(node -> node.equalsIgnoreCase("Albany") || cityGraph.adjacentNodes(node).contains("Albany")), Boolean.FALSE);
	}

}
