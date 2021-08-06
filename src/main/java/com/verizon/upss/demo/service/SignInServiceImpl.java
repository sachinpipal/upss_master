package com.verizon.upss.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verizon.upss.demo.model.User;
import com.verizon.upss.demo.repository.UserRepository;
import com.verizon.upss.demo.requestVO.UserRequestVO;
import com.verizon.upss.demo.responseVO.UserResponseVO;

@Service
@Transactional
public class SignInServiceImpl implements SignInService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserResponseVO createUser(final UserRequestVO userRequestVO) {
		User user = requestVoToDbEntity(userRequestVO);
		User savedUser = userRepository.save(user);
		UserResponseVO userResponseVO = new UserResponseVO();
		populateResponseVO(savedUser, userResponseVO);
		return userResponseVO;

	}

	private UserResponseVO populateResponseVO(User savedUser, UserResponseVO userResponseVO) {
		userResponseVO.setUserId(savedUser.getId());
		userResponseVO.setFirstName(savedUser.getfirstName());
		userResponseVO.setLastName(savedUser.getLastName());
		userResponseVO.setEmail(savedUser.getEmail());
		return userResponseVO;
	}

	@Override
	public UserResponseVO logIn(UserRequestVO userRequestVO) {
		UserResponseVO userResponseVO = new UserResponseVO();
		return userResponseVO;
	}

	private User requestVoToDbEntity(UserRequestVO userRequestVO) {
		User user = new User();
		user.setEmail(userRequestVO.getEmail());
		user.setfirstName(userRequestVO.getFirstName());
		user.setLastName(userRequestVO.getLastName());
		user.setPassword(passwordEncoder.encode(userRequestVO.getPassword()));
		return user;
	}

}
