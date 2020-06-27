package org.apache.kafka.json;


public class JsonCustomObject {
	
	private String key;
	private String message;
	public JsonCustomObject(String key, String message) {
		super();
		this.key = key;
		this.message = message;
	}
	public JsonCustomObject(){
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
