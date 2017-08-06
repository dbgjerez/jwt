package com.dbg.jwt.test.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbg.jwt.dao.UserDao;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.model.user.User;
import com.dbg.jwt.service.UserService;
import com.dbg.jwt.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

	private static final String EMAIL = "A";
	private static final String PASSWORD = "B";
	private static final User USER = new User();

	@InjectMocks
	private UserService userService = new UserServiceImpl();

	@Mock
	private UserDao userDao;

	@Before
	public void init() {
		USER.setEmail(EMAIL);
		USER.setPassword(PASSWORD);
		Mockito.when(userDao.findOneByEmailAndPassword(EMAIL, PASSWORD)).thenReturn(Optional.of(USER));
	}

	@Test
	public void testFindUserOk() throws InvalidUserException {
		final User u = userService.findUser(new LoginDTO(EMAIL, PASSWORD));

		Assert.assertNotNull(u);
		Assert.assertEquals(u.getEmail(), EMAIL);
		Assert.assertEquals(u.getPassword(), PASSWORD);
	}

	@Test(expected = InvalidUserException.class)
	public void testFindUserKo() throws InvalidUserException {
		userService.findUser(new LoginDTO(EMAIL, null));

	}

}
