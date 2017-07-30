package com.dbg.jwt.service;

import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;

public interface UserService {

	Boolean checkUser(LoginDTO login) throws InvalidUserException;

}
