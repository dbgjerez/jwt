package com.dbg.jwt.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dbg.jwt.dto.MessageDTO;
import com.dbg.jwt.exceptions.InvalidUserException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@ResponseBody
@ControllerAdvice(basePackages = "com.dbg.jwt.controller.v1")
public class V1ControllerAdvice {

	// FIXME llevar a spring cofing
	private static final String ERROR_CREDENTIALS = "Bad credentials!";
	private static final String ERROR_TOKEN_EXPIRED = "El token ha expirado";
	private static final String ERROR_SIGNATURE = "¬ ¬";

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidUserException.class)
	public MessageDTO invalidUserException(InvalidUserException e) {
		return new MessageDTO(ERROR_CREDENTIALS);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ExpiredJwtException.class)
	public MessageDTO tokenExpiredException(ExpiredJwtException e) {
		return new MessageDTO(ERROR_TOKEN_EXPIRED);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(SignatureException.class)
	public MessageDTO signatureException(SignatureException e) {
		return new MessageDTO(ERROR_SIGNATURE);
	}

}
