package com.dbg.jwt.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
import com.dbg.jwt.service.LocalDateTimeService;
import com.dbg.jwt.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

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

	@Autowired
	private LocalDateTimeService dateService;

	@Override
	public GenerateTokenDTO loginUser(LoginDTO login) throws InvalidUserException {
		final User user = userService.findUser(login);
		final String token = generateToken(user);
		return tokenMapper.map(token);
	}

	private String generateToken(final User u) {
		// FIXME generar tokens con roles de usuarios
		final LocalDateTime now = dateService.now();
		return Jwts.builder().claim(Claims.SUBJECT, u.getUsername()).claim(Claims.ISSUED_AT, toEpoch(now))
				.claim(Claims.EXPIRATION, toEpoch(dateService.plusSeconds(now, expiration)))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	private Long toEpoch(LocalDateTime date) {
		return date.atZone(DEFAULT_ZONEID).toEpochSecond();
	}

	@Override
	public Boolean validateToken(String token) throws ExpiredJwtException, SignatureException {
		final Claims claims = extractClaims(token);
		final Date exp = claims.get(Claims.EXPIRATION, Date.class);
		final LocalDateTime expAt = dateService.toLocalDateTime(exp);
		return dateService.now().isBefore(expAt);
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

}