package com.cognizant.customerauthenticationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.customerauthenticationservice.util.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	/**Object which holds the user details*/
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**JWT Filter which will filter the incoming requests*/
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/**
	 *	@param auth
	 *	Building authentication manager
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		super.configure(auth);
		auth.userDetailsService(userDetailsService);
	}

	/**
	 *	@param http instance of HttpSecurity
	 *	Configuring Filter chain and adding JWT filter to it
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated()
				.and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * @param web which is a instance of WebSecurity
	 * Defining the url's which doesn't need to be authenticated
	 */
	@Override
	public void configure(WebSecurity web) throws Exception 
	{
		web.ignoring().antMatchers("/authenticate","/h2-console/**","/v2/api-docs","/configuration/ui",
					"/swagger-resources/**","/configuration/security","/swagger-ui.html","/webjars/**","/authapp/swagger");
	}

	/**
	 *	Creating authentication manager bean
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception 
	{
		return super.authenticationManagerBean();
	}
}
