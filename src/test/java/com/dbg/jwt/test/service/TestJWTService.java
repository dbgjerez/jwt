package com.dbg.jwt.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbg.jwt.service.JWTService;
import com.dbg.jwt.service.impl.JWTServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestJWTService {

	@InjectMocks
	JWTService service = new JWTServiceImpl();

	@Test
	public void createToken() {
	}

}
