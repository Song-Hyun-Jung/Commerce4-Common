package com.digital4.context;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.digital4.schema.Auth;
import com.digital4.utils.HttpConnectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthContext {
	

	
	public Auth isValidToken(long token) throws Exception{
		String targetUrl = "APIG";
		String requestUrl = "/rest/auth/tokenValid";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		String response = HttpConnectionUtils.postRequest(targetUrl, requestUrl, map);
		System.out.println("postRequest:" + response);
		ObjectMapper objectMapper = new ObjectMapper();
		Auth auth = objectMapper.readValue(response, Auth.class);
		return auth;
	}
	
	public Auth updateValidTime(long token) throws Exception{
		String targetUrl = "APIG";
		String requestUrl = "/rest/auth/updateToken";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		String response = HttpConnectionUtils.postRequest(targetUrl, requestUrl, map);
		System.out.println("postRequest:" + response);
		ObjectMapper objectMapper = new ObjectMapper();
		Auth auth = objectMapper.readValue(response, Auth.class);
		return auth;
	}
	
	public Auth getAuthPersonId(long token) throws Exception{
		String targetUrl = "APIG";
		String requestUrl = "/rest/auth/personInfo";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		String response = HttpConnectionUtils.postRequest(targetUrl, requestUrl, map);
		System.out.println("postRequest:" + response);
		ObjectMapper objectMapper = new ObjectMapper();
		Auth auth = objectMapper.readValue(response, Auth.class);
		return auth;
	}

	public Auth generateToken(long personId) throws Exception {
		String targetUrl = "APIG";
		String requestUrl = "/rest/auth/generateToken";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("personId", personId);
		String response = HttpConnectionUtils.postRequest(targetUrl, requestUrl, map);
		System.out.println("postRequest:" + response);
		ObjectMapper objectMapper = new ObjectMapper();
		Auth auth = objectMapper.readValue(response, Auth.class);
		return auth;
	}
}
