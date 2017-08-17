package com.dbg.jwt.controller.v1.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.jwt.controller.v1.auth.AuthController;
import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.service.JWTService;

@RestController
@RequestMapping(value = "/v1/auth")
public class AuthControllerImpl implements AuthController {

	@Autowired
	private JWTService jwtService;

	@Override
	public ResponseEntity<?> token(@RequestBody LoginDTO login) throws InvalidUserException {
		final GenerateTokenDTO res = jwtService.loginUser(login);
		return ResponseEntity.ok(res);
	}

}
