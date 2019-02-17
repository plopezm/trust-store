package com.aeox.truststore.generator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeox.truststore.generator.entities.Key;
import com.aeox.truststore.generator.services.TrustGeneratorService;

@RestController
@RequestMapping("/store")
public class TrustGeneratorController {

	@Autowired
	private TrustGeneratorService trustStore;
	
	@GetMapping("/public")
	public Key getPublicKey() {
		return trustStore.getPublicKey();
	}
	
	@GetMapping("/private")
	public Key getPrivateKey() {
		return trustStore.getPrivateKey();
	}	
}
