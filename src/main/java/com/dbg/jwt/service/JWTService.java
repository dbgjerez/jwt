package com.dbg.jwt.service;

import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;

public interface JWTService {

	GenerateTokenDTO generateToken(LoginDTO login) throws InvalidUserException;

	Boolean validateToken(String token);

}
