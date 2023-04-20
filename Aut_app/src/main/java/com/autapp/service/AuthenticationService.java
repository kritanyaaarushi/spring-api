package com.autapp.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.autapp.JWT.AuthenticationRequest;
import com.autapp.JWT.AuthenticationResponse;
import com.autapp.JWT.JWTUtil;
import com.autapp.JWT.RegisterRequest;
import com.autapp.entity.Role;
import com.autapp.entity.Token;
import com.autapp.entity.TokenType;
import com.autapp.entity.User;
//import com.autapp.entity.User.UserBuilder;
import com.autapp.repo.TokenRepository;
import com.autapp.repo.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService  {
   @Autowired
	private  UserRepository userRepo;
   
   @Autowired
   private TokenRepository tokenRepo;
   
   @Autowired
	PasswordEncoder passwordEncoder;
   
   @Autowired
   JWTUtil jwtUtil;
   
   @Autowired
	private AuthenticationManager authenticationManager;
   
   
   
   
   public AuthenticationResponse register(RegisterRequest request){
	   //var user=User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
			   //.role(Role.USER).build();
			   var user=User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
					   .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(request.getRole()).build();
	   
	   var savedUser=userRepo.save(user);
	   
	    var jwtToken=jwtUtil.generateToken(user);
	   var refreshToken=jwtUtil.generateRefreshToken(user);

	   saveUserToken(savedUser,jwtToken);
	   return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
 // return AuthenticationResponse.builder().token(jwtToken).build();
   }
   
   
   private void saveUserToken(User user, String jwtToken) {
	var token=Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
	
	   tokenRepo.save(token);
    }
   
   public AuthenticationResponse authenticate(AuthenticationRequest request){
	   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
	   var user=userRepo.findByEmail(request.getEmail()).orElseThrow();
	   var jwtToken=jwtUtil.generateToken(user);
	   var refreshToken=jwtUtil.generateRefreshToken(user);
	   revokeAllUserTokens(user);
	   saveUserToken(user,jwtToken);
	  return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
	   //return AuthenticationResponse.builder().token(jwtToken).build();
	   
   }
   
   public AuthenticationResponse resetPassword(AuthenticationRequest request) {
	   User user=userRepo.findByEmail(request.getEmail()).orElseThrow();
	   if(user!=null) {
		   if(user.getResetPasswordAttempts() >=3) {
			   throw new RuntimeException("Reset password Attempts limit exceeded");
		   }
		   user.setPassword(passwordEncoder.encode(request.getPassword()));
		   user.setResetPasswordAttempts(user.getResetPasswordAttempts()+1);
		   userRepo.save(user);
		   var jwtToken=jwtUtil.generateToken(user);
		   var refreshToken=jwtUtil.generateRefreshToken(user);
		   revokeAllUserTokens(user);
		   saveUserToken(user,jwtToken);
		   return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
	   }
	   else {
		   throw new RuntimeException("No value present");
	   }
   }

  private void revokeAllUserTokens(User user) {
	var validUserTokens=tokenRepo.findAllValidTokenByUser(user.getId());
	//List<Token> validUserTokens=tokenRepo.findAllValidTokenByUser(user.getId());
	if(validUserTokens.isEmpty())
		return;
		
	validUserTokens.forEach(token->{
		token.setExpired(true);
		token.setRevoked(true);
	});
	 tokenRepo.saveAll(validUserTokens);
   }
  
  

 public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException{
	
	 final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
	 final String refreshToken;
	 final String userEmail;
	 if(authHeader==null || !authHeader.startsWith("Bearer")) {
		 return;
	 }
	 refreshToken=authHeader.substring(7);
	 userEmail=jwtUtil.extractUsername(refreshToken);
	 if(userEmail!=null) {
		 var user=userRepo.findByEmail(userEmail).orElseThrow();
		 if(jwtUtil.isTokenValid(refreshToken,user)) {
			 var accessToken=jwtUtil.generateToken(user);
			 revokeAllUserTokens(user);
			 saveUserToken(user,accessToken);
			 var authResponse=AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
			 new ObjectMapper().writeValue(response.getOutputStream(),authResponse);
		 }
	 }
	 
	
 }




{/*
  public AuthenticationResponse register(RegisterRequest request) {
	   
	  var user=User.builder().firstname(request.getFirstname()).lastname(request.getLastname()).email(request.getEmail()).password(bCryptEncoder.encode(request.getPassword())).roles(request.getRoles()).build();
	var savedUser= userRepo.save(user);
	var jwtToken=jwtUtil.generateToken(user);
	saveUserToken(savedUser,jwtToken);
			return AuthenticationResponse.builder().token(jwtToken).build();
	
   }
  
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
	  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
  var user=userRepo.findByEmail(request.getEmail()).orElseThrow();
  var jwtToken=jwtUtil.generateToken(user);
  revokeAllUserTokens(user);
  saveUserToken(user,jwtToken);
  return AuthenticationResponse.builder().token(jwtToken).build();
  
  }
  


private void saveUserToken(User user, String jwtToken) {
	var token=Token.builder().token(jwtToken).tokenType(TokenType.BEARER)
	
}


private void revokeAllUserTokens(User user) {
	
	var validUserTokens=tokenRepo.findAllValidTokenByUser(user.getId());
	if(validUserTokens.isEmpty()) 
		return;
	validUserTokens.forEach(token->{
		token.setExpired(true);
		token.setRevoked(true);
		
	});
	tokenRepo.saveAll(validUserTokens);
		
	
}

*/}
}