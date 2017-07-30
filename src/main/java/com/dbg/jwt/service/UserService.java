package com.dbg.jwt.service;

import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.model.user.User;

public interface UserService {

	User findUser(LoginDTO login) throws InvalidUserException;

}
