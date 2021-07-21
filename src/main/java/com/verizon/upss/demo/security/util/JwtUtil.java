//package com.verizon.upss.demo.security.util;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
//import com.verizon.upss.demo.security.services.UserDetailsImpl;
//
//@Component
//public class JwtUtil {
//	
//	  @Value("${verizon.app.jwtSecret}")
//	  private String jwtSecret;
//
//	  @Value("${verizon.app.jwtExpirationMs}")
//	  private int jwtExpirationMs;
//	  
//	  public String generateJwtToken(Authentication authentication) {
//
//		    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//
//		    return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
//		        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
//		        .compact();
//		  }
//
//}
