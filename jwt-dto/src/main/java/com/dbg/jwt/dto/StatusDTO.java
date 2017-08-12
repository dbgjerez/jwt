package com.dbg.jwt.dto;

import java.io.Serializable;

public class StatusDTO implements Serializable {

	private static final long serialVersionUID = 2275012616720606708L;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
