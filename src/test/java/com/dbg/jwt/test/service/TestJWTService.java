package com.dbg.jwt.test.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Before;
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

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@RunWith(MockitoJUnitRunner.class)
public class TestJWTService {

	private static final String SECRET = "test";
	private static final String EMAIL = "test@test.com";
	private static final String PASS = "pass";
	private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjk0NjcyNDQwMCwiZXhwIjo5NDY3MjQ0NjB9.tIRYmvjmn7kKq5o0PhRdTfmMpCzzZhCXwNayIENzsVo";
	private static final String TOKEN_INVALID = "eyJhbGciOiJIUzI1NiJ9.EyJpYXQiOjk0NjcyNDQwMCwiZXhwIjo5NDY3MjQ0NjB9.tIRYmvjmn7kKq5o0PhRdTfmMpCzzZhCXwNayIENzsVo";
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

	@Before
	public void init() {
		ReflectionTestUtils.setField(service, "secret", SECRET);
		ReflectionTestUtils.setField(service, "expiration", EXPIRATION);
	}

	@Test
	public void createToken() throws InvalidUserException {
		final LoginDTO login = createLoginDTO(EMAIL, PASS);
		Mockito.when(userService.findUser(login)).thenReturn(createUser(EMAIL, PASS));
		Mockito.when(dateService.now()).thenReturn(DATE);

		final GenerateTokenDTO res = service.loginUser(login);

		Assert.assertNotNull(res);
		Assert.assertEquals(res.getAccessToken(), TOKEN);
	}

	@Test
	public void validateTokenOk() throws InvalidUserException {
		final LocalDateTime now = LocalDateTime.now();
		final LoginDTO login = createLoginDTO(EMAIL, PASS);
		Mockito.when(dateService.now()).thenReturn(now);
		Mockito.when(dateService.plusSeconds(now, EXPIRATION)).thenReturn(now.plus(EXPIRATION, ChronoUnit.SECONDS));
		Mockito.when(userService.findUser(login)).thenReturn(createUser(EMAIL, PASS));

		final GenerateTokenDTO tk = service.loginUser(login);

		final Boolean validation = service.validateToken(tk.getAccessToken());
		Assert.assertTrue(validation);
	}

	@Test(expected = ExpiredJwtException.class)
	public void validateTokenExpiredToken() {
		service.validateToken(TOKEN);
	}

	@Test(expected = SignatureException.class)
	public void validateTokenInvalid() {
		service.validateToken(TOKEN_INVALID);
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
