package com.verizon.upss.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.upss.demo.commons.util.JwtUtil;
import com.verizon.upss.demo.commons.util.MessageResponse;
import com.verizon.upss.demo.exception.UpssServiceExcpetion;
import com.verizon.upss.demo.repository.UserRepository;
import com.verizon.upss.demo.request.SignInRequestVO;
import com.verizon.upss.demo.request.UserRequestVO;
import com.verizon.upss.demo.response.UserResponseVO;
import com.verizon.upss.demo.service.SignInService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/v1/user")
public class SignInController {

	@Autowired
	private SignInService signInService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;

	@Operation(summary = "User Registration form")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User registered successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Mandatory fields not found", content = @Content) })
	@PostMapping(value = "/signUp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> signUp(@Valid @RequestBody UserRequestVO userRequestVO) {
		boolean isUserExist = userRepository.existsByEmail(userRequestVO.getEmail());
		if (isUserExist) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!", ""));
		}
		UserResponseVO userResponse = signInService.createUser(userRequestVO);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!", userResponse));

	}

	@Operation(summary = "User Sigin form")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User logged-in successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = String.class)) }),
			@ApiResponse(responseCode = "404", description = "Mandatory fields not found", content = @Content) })
	@PostMapping("/signin")
	public ResponseEntity<MessageResponse> logIn(@RequestBody SignInRequestVO signInRequestVO) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signInRequestVO.getEmail(), signInRequestVO.getPassword()));
		} catch (Exception ex) {
			throw new UpssServiceExcpetion(ex, ex.getMessage(), "Not able to Signin", 202, 0);
		}
		return ResponseEntity.ok(new MessageResponse("Access token generated successfully!",
				jwtUtil.generateToken(signInRequestVO.getEmail())));
	}
}
