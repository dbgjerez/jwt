package com.dbg.jwt.service;

import javax.servlet.http.HttpServletRequest;

import com.dbg.jwt.exceptions.InvalidRequestException;

public interface ServletRequestService {

	String extractToken(HttpServletRequest request) throws InvalidRequestException;

}
