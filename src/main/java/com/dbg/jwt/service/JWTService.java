package com.dbg.jwt.service;

import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.dto.ValidTokenDTO;
import com.dbg.jwt.exceptions.InvalidUserException;

public interface JWTService {

	GenerateTokenDTO loginUser(LoginDTO login) throws InvalidUserException;

	ValidTokenDTO validateToken(String token);

}
