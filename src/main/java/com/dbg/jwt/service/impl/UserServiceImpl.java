package com.dbg.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
		final User user = new User();
		user.setEmail(login.getEmail());
		user.setPassword(login.getPassword());
		return userDao.findOne(Example.of(user)).orElseThrow(() -> new InvalidUserException());
	}

}
