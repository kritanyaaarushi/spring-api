package com.autapp.JWT;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.autapp.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	
	//private static final String SECRET_KEY="5468566D597133743677397A24432646294A404E635266556A586E3272347537";
	@Value("${application.security.jwt.secret-key}")
	private String secretKey;
	@Value("${application.security.jwt.expiration}")
	private long jwtExpiration;
	@Value("${application.security.jwt.refresh-token.expiration}")
	private long refreshExpiration;
	
	public String extractUsername(String token){
		return extractClaim(token,Claims::getSubject);

	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
		}
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}
	public String generateToken(Map<String,Object>extraClaims,UserDetails userDetails) {
		
		//return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
			//		.setIssuedAt(new Date(System.currentTimeMillis()))
				//.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
					//.signWith(getSignInKey(),SignatureAlgorithm.HS256)
					//.compact();
		
		return buildToken(extraClaims,userDetails,jwtExpiration);
		}
	
	public String generateRefreshToken(UserDetails userDetails) {
		return buildToken(new HashMap<>(),userDetails,refreshExpiration);
	}
	
	private String buildToken(Map<String,Object>extraClaims,UserDetails userDetails,long expiration) {
		return Jwts.builder().setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				
				.compact();
	}
	
	
	private Claims extractAllClaims(String token){
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return(username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	//private Claims extractAllClaims(String token) {
		//return Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
			//	.parseClaimsJws(token).getBody();
	//}
	
	private Key getSignInKey(){
		byte[]keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	{/*
	
	@Value("${application.security.jwt.secret-key}")
	private String secretKey;
	
	@Value("${application.security.jwt.expiration}")
	private long jwtExpiration;
	
	@Value("${application.security.jwt.refresh-token.expiration}")
	private long refreshExpiration;

	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	
	public <T> T extractClaim(String token,Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
		}
	
	//public String generateToken(User user) {
		//return generateToken(user);
	//}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(),userDetails);
	}
	

//	public String generateToken(Map<String,Object>extraClaims,UserDetails userDetails) {
		
	//return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
		//		.setIssuedAt(new Date(System.currentTimeMillis()))
			//	.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				//.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				//.compact();
	//}
	
	public String generateToken(Map<String,Object>extraClaims,UserDetails userDetails) {
		return buildToken(extraClaims,userDetails,jwtExpiration);
	}
	
	public String generateRefreshToken(UserDetails userDetails) {
		return buildToken(new HashMap<>(),userDetails,refreshExpiration);
	}
	
	private String buildToken(Map<String,Object>extraClaims,UserDetails userDetails,long expiration) {
		return Jwts.builder().setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(getSignInKey(),SignatureAlgorithm.HS256)
				
				.compact();
	}
	
	
	public boolean isTokenValid(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return(username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
				.parseClaimsJws(token).getBody();
	}
	private Key getSignInKey() {
		byte[] keyBytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}


*/}
}
