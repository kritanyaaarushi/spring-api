package com.autapp.JWT;



import java.io.Serializable;

import org.springframework.security.core.userdetails.User.UserBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

//import com.autapp.entity.Token.UserBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable{
	
	//private String token;
	
	@JsonProperty
	private String accessToken;
	
	public String getAccessToken() {
		return accessToken;
	}

	public AuthenticationResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public void setAccessToken(String accessToken) {
	
	this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	

 


@JsonProperty
   private String refreshToken;
	
	
  //public String getToken() {
	//	return token;
	//}

//public AuthenticationResponse(String token) {
	//super();
	//this.token = token;
//}

	//public void setToken(String token) {
		//this.token = token;
	//}
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public static class UserBuilder {
		private String accessToken;
		private String refreshToken;
		//private String token;
		//public UserBuilder token(String token) {
		//	this.token=token;
			//return this;
		//}
		
		public UserBuilder accessToken(String accessToken) {
			this.accessToken=accessToken;
			return this;
		}
		public UserBuilder refreshToken(String refreshToken) {
			this.refreshToken=refreshToken;
			return this;
		}
		public AuthenticationResponse build() {
		//	return new AuthenticationResponse(this.token);
			//return new AuthenticationResponse(this.accessToken,this.refreshToken);
			AuthenticationResponse authen=new AuthenticationResponse(refreshToken, accessToken);
			authen.accessToken=this.accessToken;
			authen.refreshToken=this.refreshToken;
			return authen;
			
		}
	}









	{/*
	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("refresh_token")
	private String refreshToken;
	
	
	
	
	
	
	

	

	

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	public static class UserBuilder{
		private static String accessToken;
		private static String refreshToken;
		
		public UserBuilder accessToken(String accessToken) {
			this.accessToken=accessToken;
			return this;
		}
		public UserBuilder refreshToken(String refreshToken) {
			this.refreshToken=refreshToken;
			return this;
		}
		
		public AuthenticationResponse build() {
			return new AuthenticationResponse();
		}
		
	}
	
	
	*/}
	
	
	
	
	
	
	//@JsonProperty
	//private  String token;
	
	//builder
	//public static UserBuilder builder() {
		//return new UserBuilder();
	//}
 //public static class UserBuilder {
	//  private static String token;
	  
	 // public UserBuilder token(String token) {
		//  this.token=token;
		  //return this;
	  //}
	  //public AuthenticationResponse build() {
		//  return new AuthenticationResponse();
	  //}
// }
	
}
