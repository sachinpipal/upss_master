package com.verizon.upss.demo.controller;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.verizon.upss.demo.commons.util.ObjectMapperUtil;
import com.verizon.upss.demo.repository.UserRepository;
import com.verizon.upss.demo.request.UserRequestVO;
import com.verizon.upss.demo.response.UserResponseVO;
import com.verizon.upss.demo.service.SignInService;

@ExtendWith(MockitoExtension.class)
class SignInControllerTest {
	private MockMvc mockMvc;
	@Mock
	private SignInService signInService;
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private SignInController signInController;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(signInController).build();
	}

	@Test
	final void testSignUpSuccess() throws Exception {
		UserRequestVO userRequestVO = new UserRequestVO();
		userRequestVO.setEmail("sachin@gmail.com");
		userRequestVO.setFirstName("sachin");
		userRequestVO.setLastName("pipal");
		userRequestVO.setPassword("password");
		UserResponseVO userResponseVO = new UserResponseVO();
		userResponseVO.setEmail("sachin@gmail.com");
		userResponseVO.setFirstName("sachin");
		userResponseVO.setLastName("pipal");
		Mockito.when(userRepository.existsByEmail(userRequestVO.getEmail())).thenReturn(false);
		Mockito.when(signInService.createUser(any(UserRequestVO.class))).thenReturn(userResponseVO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/user/signUp")
				.accept(MediaType.APPLICATION_JSON).content(ObjectMapperUtil.objectToJson(userRequestVO))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assertions.assertEquals(200, status);
	}

}
