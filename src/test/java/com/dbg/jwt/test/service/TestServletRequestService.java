package com.dbg.jwt.test.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.dbg.jwt.exceptions.InvalidRequestException;
import com.dbg.jwt.service.ServletRequestService;
import com.dbg.jwt.service.impl.ServletRequestServiceImpl;

public class TestServletRequestService {

	private static final String TOKEN = "tk";

	private ServletRequestService service = new ServletRequestServiceImpl();

	@Test
	public void extrackToken() throws InvalidRequestException {
		final MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", TOKEN);
		final String extractToken = service.extractToken(request);
		Assert.assertEquals(extractToken, TOKEN);
	}

	@Test(expected = InvalidRequestException.class)
	public void testNoToken() throws InvalidRequestException {
		service.extractToken(new MockHttpServletRequest());
	}

}
