package com.dbg.jwt.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.mappers.TokenMapper;
import com.dbg.jwt.model.user.User;
import com.dbg.jwt.service.JWTService;
import com.dbg.jwt.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTServiceImpl implements JWTService {

	private static final ZoneId DEFAULT_ZONEID = ZoneId.systemDefault();

	@Value("${jwt.token}")
	private String secret;

	@Value("${jwt.expiration}")
	private Integer expiration;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenMapper tokenMapper;

	@Override
	public GenerateTokenDTO loginUser(LoginDTO login) throws InvalidUserException {
		final User user = userService.findUser(login);
		final String token = generateToken(user);
		return tokenMapper.map(token);
	}

	public String generateToken(final User u) {
		// FIXME generar tokens con roles de usuarios
		return Jwts.builder().claim(Claims.SUBJECT, u.getUsername())
				.claim(Claims.ISSUED_AT, LocalDateTime.now().atZone(DEFAULT_ZONEID).toEpochSecond())
				.claim(Claims.EXPIRATION, generateExpirationDate().atZone(DEFAULT_ZONEID).toEpochSecond())
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	@Override
	public Boolean validateToken(String token) {
		final Claims claims = extractClaims(token);
		final Date exp = claims.get(Claims.EXPIRATION, Date.class);
		final LocalDateTime expAt = extractLocalDateTime(exp);
		return LocalDateTime.now().isBefore(expAt);
	}

	private LocalDateTime extractLocalDateTime(Date d) {
		return LocalDateTime.ofInstant(d.toInstant(), DEFAULT_ZONEID);
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private LocalDateTime generateExpirationDate() {
		return LocalDateTime.now().plus(expiration, ChronoUnit.SECONDS);
	}

}