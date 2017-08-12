package com.dbg.jwt.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

public class GenerateTokenDTO implements Serializable {

	private static final long serialVersionUID = -1857087699760304240L;

	@JsonAlias("access_token")
	private String accesstToken;

	public String getAccesstToken() {
		return accesstToken;
	}

	public void setAccesstToken(String accesstToken) {
		this.accesstToken = accesstToken;
	}

}
