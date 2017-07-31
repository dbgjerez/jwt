package com.dbg.jwt.controller.v1.auth.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.jwt.controller.v1.auth.AuthController;
import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.dto.StatusDTO;
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

	@Override
	public StatusDTO validate(HttpServletRequest request) {
		final String header = request.getHeader("Authorization");
		System.out.println(header);
		final Boolean validateToken = jwtService.validateToken(header);
		System.out.println(validateToken);
		return new StatusDTO();
	}

}
