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
import com.dbg.jwt.exceptions.InvalidUserException;
import com.dbg.jwt.service.JWTService;

@RunWith(MockitoJUnitRunner.class)
public class TestAuthController {

	private static final String ACCESS = "ACCESS";

	private MockMvc mockMvc;

	@InjectMocks
	private AuthController controller = new AuthControllerImpl();

	@Mock
	private JWTService jwtService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new V1ControllerAdvice()).build();
	}

	@Test
	public void testTokenKo() throws Exception {
		Mockito.when(jwtService.loginUser(new LoginDTO("KO", "KO"))).thenThrow(InvalidUserException.class);
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth").contentType(ContentType.APPLICATION_JSON.getMimeType())
				.content("{\"email\":\"KO\",\"password\":\"KO\"}"))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void testTokenOk() throws Exception {
		Mockito.when(jwtService.loginUser(new LoginDTO("KO", "KO"))).thenReturn(createTokenDTO());
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth").contentType(ContentType.APPLICATION_JSON.getMimeType())
				.content("{\"email\":\"KO\",\"password\":\"KO\"}")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.accessToken", org.hamcrest.core.Is.is(ACCESS)));
	}

	private GenerateTokenDTO createTokenDTO() {
		final GenerateTokenDTO token = new GenerateTokenDTO();
		token.setAccessToken(ACCESS);
		return token;
	}

}
