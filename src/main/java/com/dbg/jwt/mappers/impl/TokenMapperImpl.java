package com.dbg.jwt.mappers.impl;

import org.springframework.stereotype.Service;

import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.mappers.TokenMapper;

@Service
public class TokenMapperImpl implements TokenMapper {

	@Override
	public GenerateTokenDTO map(String token) {
		final GenerateTokenDTO res = new GenerateTokenDTO();
		res.setAccessToken(token);
		return res;
	}

}
