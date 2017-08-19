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
import com.dbg.jwt.exceptions.InvalidRequestException;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.service.JWTService;
import com.dbg.jwt.service.ServletRequestService;

@RestController
@RequestMapping(value = "/v1/auth")
public class AuthControllerImpl implements AuthController {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private ServletRequestService requestService;

	@Override
	public ResponseEntity<?> token(@RequestBody LoginDTO login) throws InvalidUserException {
		final GenerateTokenDTO res = jwtService.loginUser(login);
		return ResponseEntity.ok(res);
	}

	@Override
	public ResponseEntity<?> validate(HttpServletRequest request) throws InvalidRequestException {
		final String header = requestService.extractToken(request);
		return ResponseEntity.ok(jwtService.validateToken(header));
	}

}
