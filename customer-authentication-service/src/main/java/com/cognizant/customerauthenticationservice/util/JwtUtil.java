package com.cognizant.customerauthenticationservice.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
	/** Contains key for signing the JWT*/
	private String secretkey = "${jwt.secret}";

	/**
	 * @param token 
	 * @return a response of another function extractClaim which will return subject of JWT
	 */
	public String extractUsername(String token) {
		log.info("INSIDE EXTRACT USERNAME");
		log.info("END OF EXTRACT USERNAME");
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * @param <T>
	 * @param token
	 * @param claimsResolver
	 * @return claims of JWT with the help of claims resolver
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		log.info("INSIDE EXTRACT CLAIMS");
		final Claims claims = extractAllClaims(token);
		log.info("END OF EXTRACT CLAIMS");
		return claimsResolver.apply(claims);
	}

	/**
	 * @param token
	 * @return all the claims of JWT
	 */
	private Claims extractAllClaims(String token) {
		log.info("INSIDE EXTRACT ALL CLAIMS");
		log.info("END OF EXTRACT ALL CLAIMS");
		return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody();
	}

	/**
	 * @param userDetails
	 * @return response of another method createToken which will return JWT TOKEN 
	 */
	public String generateToken(UserDetails userDetails) {
		log.info("INSIDE GENERATE TOKEN");
		Map<String, Object> claims = new HashMap<>();
		log.info("END OF GENERATE TOKEN");
		return createToken(claims, userDetails.getUsername());
	}

	/**
	 * @param claims
	 * @param subject
	 * @return JWT token
	 */
	private String createToken(Map<String, Object> claims, String subject) {
		log.info("INSIDE CREATE TOKEN");
		String compact = Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))// token for 15 min
				.signWith(SignatureAlgorithm.HS256, secretkey).compact();
		log.info("END OF CREATE TOKEN");
		return compact;
	}

	/**
	 * @param token
	 * @return Boolean based on the validity of the token which was 
	 * set during token creation 
	 */
	public Boolean validateToken(String token) {
		log.info("INSIDE VALIDATE TOKEN");
		try {
			Jwts.parser().setSigningKey(secretkey).parse(token).getBody();
			log.info("END OF VALIDATE TOKEN");	
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}