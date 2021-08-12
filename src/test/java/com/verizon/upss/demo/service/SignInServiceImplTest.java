package com.verizon.upss.demo.service;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.verizon.upss.demo.model.User;
import com.verizon.upss.demo.repository.UserRepository;
import com.verizon.upss.demo.request.UserRequestVO;
import com.verizon.upss.demo.response.UserResponseVO;

@ExtendWith(MockitoExtension.class)
class SignInServiceImplTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder passwordEncoder;
	@InjectMocks
	private SignInServiceImpl signInService;

	@BeforeAll
	public static void setUp() {
	}

	@Test
	final void testCreateUserSuccess() {
		UserRequestVO userRequestVO = new UserRequestVO();
		userRequestVO.setEmail("sachin@gmail.com");
		userRequestVO.setFirstName("sachin");
		userRequestVO.setLastName("pipal");
		userRequestVO.setPassword("password");
		User savedUser = new User();
		savedUser.setEmail("sachin@gmail.com");
		savedUser.setId(1);
		savedUser.setFirstName("sachin");
		savedUser.setLastName("pipal");
		Mockito.when(userRepository.save(any(User.class))).thenReturn(savedUser);
		UserResponseVO userResponseVO = signInService.createUser(userRequestVO);
		Assertions.assertEquals(savedUser.getId(), userResponseVO.getUserId());
	}

}
