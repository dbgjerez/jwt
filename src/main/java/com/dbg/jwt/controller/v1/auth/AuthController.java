package com.dbg.jwt.controller.v1.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;

@RestController
@RequestMapping(value = "/v1/auth")
public interface AuthController {

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> token(@RequestBody LoginDTO login) throws InvalidUserException;

}
