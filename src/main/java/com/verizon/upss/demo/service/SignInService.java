package com.verizon.upss.demo.service;

import com.verizon.upss.demo.request.UserRequestVO;
import com.verizon.upss.demo.response.UserResponseVO;

public interface SignInService {

	public UserResponseVO createUser(UserRequestVO userRequestVO);

	public UserResponseVO logIn(UserRequestVO userRequestVO);

}
