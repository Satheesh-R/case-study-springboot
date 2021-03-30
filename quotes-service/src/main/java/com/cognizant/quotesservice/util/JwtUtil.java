package com.cognizant.quotesservice.util;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
}