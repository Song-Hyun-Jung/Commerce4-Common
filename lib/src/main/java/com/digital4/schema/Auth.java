package com.digital4.schema;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Auth {
	
	@ApiModelProperty(required = false, position = 1, notes = "토큰검증", example = "false", dataType = "boolean")
	private boolean validToken;
	@ApiModelProperty(required = false, position = 2, notes = "토큰정보", example = "0", dataType = "long")
	private long token;
	@ApiModelProperty(required = false, position = 3, notes = "사용자 ID 정보", example = "0", dataType = "long")
	private long personId;

	
	public boolean getValidToken() {
		return validToken;
	}
	public void setValidToken(boolean validToken) {
		this.validToken = validToken;
	}
	
	public long getToken() {
		return token;
	}
	public void setToken(long token) {
		this.token = token;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}

	
}
