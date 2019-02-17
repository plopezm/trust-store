package com.aeox.truststore.generator.entities;

public class Key {

	private String algorithm;
	private int keyLen;
	private String key;
		
	public Key(String algorithm, int keyLen, String base64Key) {
		super();
		this.algorithm = algorithm;
		this.keyLen = keyLen;
		this.key = base64Key;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public int getKeyLen() {
		return keyLen;
	}

	public void setKeyLen(int keyLen) {
		this.keyLen = keyLen;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
