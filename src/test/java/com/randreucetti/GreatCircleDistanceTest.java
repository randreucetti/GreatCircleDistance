package com.randreucetti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.randreucetti.model.Customer;
import com.randreucetti.util.GreatCircleDistanceUtil;

public class GreatCircleDistanceTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<Customer> customers;
	private static final double SOURCE_LATITUDE = 53.3381985;
	private static final double SOURCE_LONGITUDE = -6.2592576;

	@Before
	public void initialize() {
		InputStream is = getClass().getResourceAsStream("/customers.json");
		customers = new ArrayList<Customer>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is));) {
			String line;
			ObjectMapper mapper = new ObjectMapper(); // using jackson to map to
														// our POJO
			while ((line = br.readLine()) != null) {
				Customer customer = mapper.readValue(line, Customer.class);
				customers.add(customer);
			}
		} catch (IOException e) {
			logger.error("Error reading line {}", e.getMessage());
		}
	}

	@Test
	public void testGetDistanceInRadians1() {
		for (Customer cust : customers) {
			Assert.assertTrue(GreatCircleDistanceUtil.getDistanceInRadians(SOURCE_LONGITUDE, SOURCE_LATITUDE,
					cust.getLongitude(), cust.getLatitude()) > 0);
		}
	}

	@Test
	public void testGetDistanceInRadians2() {
		Assert.assertTrue(GreatCircleDistanceUtil.getDistanceInRadians(SOURCE_LONGITUDE, SOURCE_LATITUDE,
				SOURCE_LONGITUDE, SOURCE_LATITUDE) == 0);
	}

	@Test
	public void testGetDistanceInKm1() {
		for (Customer cust : customers) {
			Assert.assertTrue(GreatCircleDistanceUtil.getDistanceInKm(SOURCE_LONGITUDE, SOURCE_LATITUDE,
					cust.getLongitude(), cust.getLatitude()) > 0);
		}
	}

	@Test
	public void testGetDistanceInKm2() {
		Assert.assertTrue(GreatCircleDistanceUtil.getDistanceInKm(SOURCE_LONGITUDE, SOURCE_LATITUDE, SOURCE_LONGITUDE,
				SOURCE_LATITUDE) == 0);
	}

	@Test
	public void getAllCustomersLessThan100Km() {
		// Using priority queue so we dont need to sort later on
		Queue<Customer> customersNearby = new PriorityQueue<Customer>();
		for (Customer cust : customers) {
			double distance = GreatCircleDistanceUtil.getDistanceInKm(SOURCE_LONGITUDE, SOURCE_LATITUDE,
					cust.getLongitude(), cust.getLatitude());
			if (distance <= 100) {
				customersNearby.add(cust);
			}
		}
		for (Customer cust : customersNearby) {
			logger.debug("Customer name: {}, Customer id: {}", cust.getName(), cust.getUser_id());
		}
	}
}
