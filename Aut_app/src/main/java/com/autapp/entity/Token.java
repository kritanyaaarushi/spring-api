package com.autapp.entity;

import java.io.Serializable;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.autapp.JWT.AuthenticationResponse;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(unique=true)
	private String token;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

public String getToken() {
		return token;
	}
   public void setToken(String token) {
		this.token = token;
	}
	
   public boolean isRevoked() {
		return revoked;
	}


   public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}


  public boolean isExpired() {
		return expired;
	}

  public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

public User getUser() {
		return user;
	}


public void setUser(User user) {
		this.user = user;
	}


   private boolean revoked;
	private  boolean expired;
	
	
	@Enumerated(EnumType.STRING)
	public TokenType tokenType=TokenType.BEARER;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	public User user;
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	public static class UserBuilder{
		private Integer id;
		private String token;
		private boolean revoked;
		private boolean expired;
		public User user;
		public TokenType tokenType;
		
		public UserBuilder id(Integer id) {
			this.id=id;
			return this;
		}
		public UserBuilder token(String token) {
			this.token=token;
			return this;
					
		}
		public UserBuilder revoked(boolean revoked) {
			this.revoked=revoked;
			return this;
		}
		public UserBuilder expired (boolean expired) {
			this.expired=expired;
			return this;
		}
		public UserBuilder user(User user) {
			this.user=user;
			return this;
		}
		public UserBuilder tokenType(TokenType tokenType) {
			this.tokenType=tokenType;
			return this;
		}
		public Token build() {
			Token token=new Token();
			token.id=this.id;
			token.token=this.token;
			token.revoked=this.revoked;
			token.expired=this.expired;
			token.user=this.user;
			token.tokenType=this.tokenType;
			return token;
			
			
		}
	}
	

{/*
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isRevoked() {
		return revoked;
	}
	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	public TokenType getTokenType() {
		return tokenType;
	}
	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Token(Integer id, String token, boolean revoked, boolean expired, TokenType tokenType, User user) {
		super();
		this.id = id;
		this.token = token;
		this.revoked = revoked;
		this.expired = expired;
		this.tokenType = tokenType;
		this.user = user;
	}
	public Token() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	public static class UserBuilder{
		private Integer id;
		private  String token;
		private boolean revoked;
		private  boolean expired;
		
		public TokenType tokenType;
		public User user;
		
		public UserBuilder token(String token) {
			this.token=token;
			return this;
		}
		public UserBuilder revoked(boolean revoked) {
			this.revoked=revoked;
			return this;
		}
		public UserBuilder expired(boolean expired) {
			this.expired=expired;
			return this;
		}
		public UserBuilder tokenType(TokenType tokenType) {
			this.tokenType=tokenType;
			return this;
		}
		public UserBuilder user(User user) {
			this.user=user;
			return this;
		}
		public Token build() {
			return new Token();
		}
	}

      */}

}
