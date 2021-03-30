package com.cognizant.customerauthenticationservice.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cognizant.customerauthenticationservice.service.CustomerDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

	/**JWT utility class which is used to build and parse JWT */
	@Autowired
    private JwtUtil jwtUtil;
	
	/**AgentDetailService which implements security's userdetailservice which 
	 * loads the user by the username which loads the user by the username 
	 */
	@Autowired
    private CustomerDetailsService customerDetailsService;
	
	/**
	 * @param request is a HttpRequest
	 * @param response is a HttpResponse
	 * @param filterChain is a filter chain
	 * @throws ServletException,IOException
	 * @return nothing
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info("INSIDE FILTER");
		final String authHeadder=request.getHeader("Authorization");
		String username = null;
        String jwt = null;

        if (authHeadder != null && authHeadder.startsWith("Bearer ")) {
            jwt = authHeadder.substring(7);
            try 
            {
            	username = jwtUtil.extractUsername(jwt);
            }
            catch(ExpiredJwtException expiredJwtException) {
            	expiredJwtException.getMessage();
            }
            catch(MalformedJwtException malformedJwtException) {
            	malformedJwtException.getMessage();
            }
            catch(SignatureException signatureException) {
            	signatureException.getMessage();
            }
        }
		  if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

	            UserDetails userDetails = this.customerDetailsService.loadUserByUsername(username);

	            if (jwtUtil.validateToken(jwt) != null && jwtUtil.validateToken(jwt) ) {

	                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
	                        userDetails, null, userDetails.getAuthorities());
	                usernamePasswordAuthenticationToken
	                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	            }
	        }
		  log.info("END OF FILTER");
		  filterChain.doFilter(request, response);
	}

}