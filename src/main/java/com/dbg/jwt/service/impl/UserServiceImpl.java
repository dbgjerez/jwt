package com.dbg.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbg.jwt.dao.UserDao;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.model.user.User;
import com.dbg.jwt.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findUser(LoginDTO login) throws InvalidUserException {
		return userDao.findOneByEmailAndPassword(login.getEmail(), login.getPassword())
				.orElseThrow(() -> new InvalidUserException());
	}

}
