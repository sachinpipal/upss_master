package com.verizon.upss.demo.service;

import com.verizon.upss.demo.requestVO.UserRequestVO;
import com.verizon.upss.demo.responseVO.UserResponseVO;

public interface SignInService {

	public UserResponseVO createUser(UserRequestVO userRequestVO);

	public UserResponseVO logIn(UserRequestVO UserRequestVO);

}
