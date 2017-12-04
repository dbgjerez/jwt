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
import com.dbg.jwt.dto.MessageDTO;
import com.dbg.jwt.dto.ValidTokenDTO;
import com.dbg.jwt.exceptions.InvalidRequestException;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.service.JWTService;
import com.dbg.jwt.service.ServletRequestService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1/auth")
public class AuthControllerImpl implements AuthController {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private ServletRequestService requestService;

	@Override
	@ApiOperation(notes = "Genera un token en caso de ser válidos los datos de registro", tags = {
			"Login" }, value = "Login user")
	@ApiResponses({ @ApiResponse(code = 200, response = GenerateTokenDTO.class, message = "Login!"),
			@ApiResponse(code = 401, response = MessageDTO.class, message = "Bad credentials") })
	public ResponseEntity<GenerateTokenDTO> token(@RequestBody LoginDTO login) throws InvalidUserException {
		final GenerateTokenDTO res = jwtService.loginUser(login);
		return ResponseEntity.ok(res);
	}

	@Override
	@ApiOperation(notes = "Comprueba la validez de un token", tags = { "Login" }, value = "Validate token")
	@ApiResponses({ @ApiResponse(code = 200, response = ValidTokenDTO.class, message = ""),
			@ApiResponse(code = 401, response = MessageDTO.class, message = "Bad credentials") })
	public ResponseEntity<ValidTokenDTO> validate(HttpServletRequest request) throws InvalidRequestException {
		final String header = requestService.extractToken(request);
		return ResponseEntity.ok(jwtService.validateToken(header));
	}

}
