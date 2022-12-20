package com.digital4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digital4.context.AuthContext;
import com.digital4.schema.Auth;

@Component
public class AuthInterceptService {
//	@Autowired
//	AuthContext authContext;

	public boolean isValidToken(Long token) throws Exception{
		AuthContext authContext = new AuthContext();
		try {
			//HttpUrlConnection-바운디드 컨텍스트
			Auth auth = authContext.isValidToken(token);
			if(auth.getValidToken() == true)
				return true;
			else
				return false;
		}
		catch(Exception e) {
			throw e;
		}
	}
	public synchronized boolean updateValidTime (long token) throws Exception{
		AuthContext authContext = new AuthContext();
		try {
			//HttpUrlConnection-바운디드 컨텍스트
			Auth auth = authContext.updateValidTime(token);
			if(auth.getValidToken() == true)
				return true;
			else
				return false;
		}
		catch(Exception e) {
			throw e;
		}
	}

	public synchronized Long getAuthPersonId(long token) throws Exception{ //token으로 personId 반환
		AuthContext authContext = new AuthContext();
		try {
			//HttpUrlConnection-바운디드 컨텍스트
			Auth auth = authContext.getAuthPersonId(token);
			return auth.getPersonId();
		}
		catch (Exception e) {
			throw e;
		}

	}
	
	
}
