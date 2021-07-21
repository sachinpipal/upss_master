package com.verizon.upss.demo.requestVO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class SignInRequestVO {
	@NotBlank
	@Size(max = 120)
	private String password;
	@NotBlank
	@Size(max = 50)
	@Email(message = "Email field required")
	private String email;

}
