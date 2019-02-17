package com.aeox.truststore.generator.services;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aeox.truststore.generator.entities.Key;

@Service
public class TrustGeneratorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrustGeneratorService.class);
	
	private String algorithm;
	private int keyLen;
	private KeyPairGenerator keyGen;
	
	private Key privateKey;
	private Key publicKey;

	
	public TrustGeneratorService(
			@Value("${truststore.generator.keylen:4096}") final int keyLen,
			@Value("${truststore.generator.algorithm:RSA}") final String alg
			) throws NoSuchAlgorithmException {
		this.algorithm = alg;
		this.keyLen = keyLen;		
		this.generateNewKeys();
	}
	
	@Scheduled(cron="${truststore.refreshKey:0 0 0/24 ? * * *}")
    public void generateNewKeys() throws NoSuchAlgorithmException {
		LOGGER.info("Generating new keys with algorithm '{}' and key length '{}'", this.algorithm, this.keyLen);
		this.keyGen = KeyPairGenerator.getInstance(this.algorithm);
		this.keyGen.initialize(this.keyLen);
		final KeyPair pair = this.keyGen.generateKeyPair();
		this.privateKey = new Key(this.algorithm, this.keyLen, Base64.encodeBase64String(pair.getPrivate().getEncoded()));
		this.publicKey = new Key(this.algorithm, this.keyLen, Base64.encodeBase64String(pair.getPublic().getEncoded()));
    }
	
	public Key getPublicKey() {
		return this.publicKey;
	}
	
	public Key getPrivateKey() {
		return this.privateKey;
	}	
}
