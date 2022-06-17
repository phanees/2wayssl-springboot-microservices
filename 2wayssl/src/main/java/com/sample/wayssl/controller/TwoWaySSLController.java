package com.sample.wayssl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/2wayssl-ms")
public class TwoWaySSLController {
	
	@GetMapping(value = "/data")
	public String getData() {
		System.out.println("Returning data from TwoWaySSLController data method");
		return "Hello from TwoWaySSLController data method";
	}

}
