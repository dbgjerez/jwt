package com.dbg.jwt.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ValidTokenDTO implements Serializable {

	private static final long serialVersionUID = 3767909442204465399L;

	private String username;

}
