package com.sample.waysslgw.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/2wayssl-gw")
public class TwoWaySSLGatewayController {
	
	@Value("${endpoint.2wayssl-service}")
    private String twoWaySslServiceEndpoint;

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value = "/data")
	public String getData() {
		System.out.println("Returning data from own data method");
		return "Hello from TwoWaySSLGatewayController data method";
	}
	
	@GetMapping(value = "/2wayssl-service-data")
	public String getDataFrom2WaySSLService() {
		System.out.println("Inside TwoWaySSLGatewayController --> getDataFrom2WaySSLService()");
		try {
			System.out.println("2 Way SSL Service Endpoint :[ " + twoWaySslServiceEndpoint + " ]");
			return restTemplate.getForObject(new URI(twoWaySslServiceEndpoint), String.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Exception occurred";
	}
	
	
}
