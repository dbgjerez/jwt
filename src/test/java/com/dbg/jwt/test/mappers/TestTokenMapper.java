package com.dbg.jwt.test.mappers;

import org.junit.Assert;
import org.junit.Test;

import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.mappers.TokenMapper;
import com.dbg.jwt.mappers.impl.TokenMapperImpl;

public class TestTokenMapper {

	private static final String TOKEN = "TOKEN";

	TokenMapper tokenMapper = new TokenMapperImpl();

	@Test
	public void testMapperToken() {
		final GenerateTokenDTO tk = tokenMapper.map(TOKEN);
		Assert.assertEquals(tk.getAccesstToken(), TOKEN);
	}

}
