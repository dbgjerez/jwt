package com.dbg.jwt.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO implements Serializable {

	private static final long serialVersionUID = -5138305849572270511L;

	private String email;
	private String password;

}
