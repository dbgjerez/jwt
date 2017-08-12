package com.dbg.jwt.test.service;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.mappers.TokenMapper;
import com.dbg.jwt.mappers.impl.TokenMapperImpl;
import com.dbg.jwt.model.user.User;
import com.dbg.jwt.service.JWTService;
import com.dbg.jwt.service.LocalDateTimeService;
import com.dbg.jwt.service.UserService;
import com.dbg.jwt.service.impl.JWTServiceImpl;
import com.dbg.jwt.service.impl.LocalDateTimeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestJWTService {

	private static final String SECRET = "test";
	private static final String EMAIL = "test@test.com";
	private static final String PASS = "pass";
	private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjk0NjcyNDQwMCwiZXhwIjo5NDY3MjQ0NjB9.tIRYmvjmn7kKq5o0PhRdTfmMpCzzZhCXwNayIENzsVo";
	private static final Integer EXPIRATION = 60;
	private static final LocalDateTime DATE = LocalDateTime.of(2000, 01, 01, 12, 00);

	@InjectMocks
	JWTService service = new JWTServiceImpl();

	@Mock
	private UserService userService;

	@Spy
	private TokenMapper tokenMapper = new TokenMapperImpl();

	@Spy
	private LocalDateTimeService dateService = new LocalDateTimeServiceImpl();

	@Test
	public void createToken() throws InvalidUserException {
		ReflectionTestUtils.setField(service, "secret", SECRET);
		ReflectionTestUtils.setField(service, "expiration", EXPIRATION);
		final LoginDTO login = createLoginDTO(EMAIL, PASS);
		Mockito.when(userService.findUser(login)).thenReturn(createUser(EMAIL, PASS));
		Mockito.when(dateService.now()).thenReturn(DATE);

		final GenerateTokenDTO res = service.loginUser(login);

		Assert.assertNotNull(res);
		Assert.assertEquals(res.getAccesstToken(), TOKEN);
	}

	private User createUser(String email, String pass) {
		final User u = new User();
		u.setEmail(email);
		u.setPassword(pass);
		return u;
	}

	private LoginDTO createLoginDTO(String email, String pass) {
		return new LoginDTO(email, pass);
	}

}
