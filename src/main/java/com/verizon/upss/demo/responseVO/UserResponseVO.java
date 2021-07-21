package com.verizon.upss.demo.responseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseVO {

	private Integer userId;
	private String email;
	private String firstName;
	private String lastName;
	private String accessToken;

}
