package com.dbg.jwt.service;

public interface JWTService {

	String generateToken(String username);

	Boolean validateToken(String token);

}
