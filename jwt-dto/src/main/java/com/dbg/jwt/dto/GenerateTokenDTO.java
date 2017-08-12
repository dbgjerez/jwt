package com.dbg.jwt.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class GenerateTokenDTO implements Serializable {

	private static final long serialVersionUID = -1857087699760304240L;

	@JsonAlias("access_token")
	private String accesstToken;

}
