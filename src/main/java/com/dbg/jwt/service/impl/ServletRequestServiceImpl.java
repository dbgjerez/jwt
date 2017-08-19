package com.dbg.jwt.service.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.dbg.jwt.exceptions.InvalidRequestException;
import com.dbg.jwt.service.ServletRequestService;

@Service
public class ServletRequestServiceImpl implements ServletRequestService {

	private static final String HEADER_NAME = "Authorization";

	@Override
	public String extractToken(HttpServletRequest request) throws InvalidRequestException {
		return Optional.ofNullable(request.getHeader(HEADER_NAME)).orElseThrow(InvalidRequestException::new);
	}

}
