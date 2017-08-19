package com.dbg.jwt.test.controller.v1.auth;

import org.apache.http.entity.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dbg.jwt.controller.v1.V1ControllerAdvice;
import com.dbg.jwt.controller.v1.auth.AuthController;
import com.dbg.jwt.controller.v1.auth.impl.AuthControllerImpl;
import com.dbg.jwt.dto.GenerateTokenDTO;
import com.dbg.jwt.dto.LoginDTO;
import com.dbg.jwt.dto.ValidTokenDTO;
import com.dbg.jwt.exceptions.InvalidRequestException;
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.service.JWTService;
import com.dbg.jwt.service.ServletRequestService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@RunWith(MockitoJUnitRunner.class)
public class TestAuthController {

	private static final String ACCESS = "ACCESS";
	private static final String JSON_KO = "{\"email\":\"KO\",\"password\":\"KO\"}";
	private static final String USERNAME = "OK";

	private MockMvc mockMvc;

	@InjectMocks
	private AuthController controller = new AuthControllerImpl();

	@Mock
	private JWTService jwtService;

	@Mock
	private ServletRequestService requestService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new V1ControllerAdvice()).build();
	}

	@Test
	public void testTokenKoInvalidUserException() throws Exception {
		Mockito.when(jwtService.loginUser(new LoginDTO("KO", "KO"))).thenThrow(InvalidUserException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth").contentType(ContentType.APPLICATION_JSON.getMimeType())
				.content(JSON_KO)).andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void testTokenKoExpiredJwtException() throws Exception {
		Mockito.when(jwtService.validateToken(Mockito.any())).thenThrow(ExpiredJwtException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/auth/validate")
				.contentType(ContentType.APPLICATION_JSON.getMimeType()).content(JSON_KO))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void testTokenKoSignatureException() throws Exception {
		Mockito.when(jwtService.validateToken(Mockito.any())).thenThrow(SignatureException.class);
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/auth/validate")
				.contentType(ContentType.APPLICATION_JSON.getMimeType()).content(JSON_KO))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void testTokenKoInvalidRequestException() throws Exception {
		Mockito.when(requestService.extractToken(Mockito.any())).thenThrow(new InvalidRequestException());
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/auth/validate")
				.contentType(ContentType.APPLICATION_JSON.getMimeType()).content(JSON_KO))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void testTokenOk() throws Exception {
		Mockito.when(jwtService.loginUser(new LoginDTO("KO", "KO"))).thenReturn(createTokenDTO());
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth").contentType(ContentType.APPLICATION_JSON.getMimeType())
				.content("{\"email\":\"KO\",\"password\":\"KO\"}")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.accessToken", org.hamcrest.core.Is.is(ACCESS)));
	}

	@Test
	public void testTokenValidationOk() throws Exception {
		Mockito.when(requestService.extractToken(Mockito.any())).thenReturn(ACCESS);
		Mockito.when(jwtService.validateToken(ACCESS)).thenReturn(new ValidTokenDTO(USERNAME));
		mockMvc.perform(
				MockMvcRequestBuilders.get("/v1/auth/validate").contentType(ContentType.APPLICATION_JSON.getMimeType()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username", org.hamcrest.core.Is.is(USERNAME)));
	}

	private GenerateTokenDTO createTokenDTO() {
		final GenerateTokenDTO token = new GenerateTokenDTO();
		token.setAccessToken(ACCESS);
		return token;
	}

}
