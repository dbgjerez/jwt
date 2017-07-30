package com.dbg.jwt.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dbg.jwt.dto.MessageDTO;
import com.dbg.jwt.exceptions.InvalidUserException;

@ResponseBody
@ControllerAdvice(basePackages = "com.dbg.jwt.controller.v1")
public class V1ControllerAdvice {

	// FIXME llevar a spring cofing
	private static final String INVALID_USER = "Bad credentials!";

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidUserException.class)
	public MessageDTO invalidUserException(InvalidUserException e) {
		return new MessageDTO(INVALID_USER);
	}

}
