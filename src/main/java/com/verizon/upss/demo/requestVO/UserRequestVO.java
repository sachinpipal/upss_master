package com.verizon.upss.demo.requestVO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class UserRequestVO {
	@NotBlank
	@Size(max = 50)
	private String firstName;
	@NotBlank
	@Size(max = 50)
	private String lastName;
	@NotBlank
	@Size(max = 120)
	private String password;
	@NotBlank
	@Size(max = 50)
	@Email(message="Email field required")
	private String email;

}
