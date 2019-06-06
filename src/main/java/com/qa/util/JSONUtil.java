package com.qa.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {

	ObjectMapper om;

	public JSONUtil() {
		om = new ObjectMapper();
	}

	public String getJSONForObject(Object obj) {
		try {
			return om.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	

	}

	public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		try {
			return om.readValue(jsonString, clazz);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null ; 
			
	}
	
	public String returnMessage(String s) {
		return "{\"message\": \"" + s + "\"";
	}

}