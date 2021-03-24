package com.cognizant.portal.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.cognizant.portal.feign.AuthenticationProxy;
import com.cognizant.portal.feign.QuotesProxy;
import com.cognizant.portal.model.AuthenticationRequest;
import com.cognizant.portal.model.AuthenticationResponse;
import com.cognizant.portal.model.CustomerDetails;
import com.cognizant.portal.model.CustomerPersonalDetails;
import com.cognizant.portal.model.Quotes;

@Service
public class PortalServiceImpl {
	
	@Autowired
	private AuthenticationProxy authenticationProxy;
	@Autowired
	private QuotesProxy quotesProxy;
	private AuthenticationResponse response;
	public String authenticate(AuthenticationRequest authenticationRequest,HttpSession httpSession,ModelMap modelMap) {
		try {
			response = authenticationProxy.authenticateUser(authenticationRequest).getBody();
		}
		catch(feign.RetryableException exception) {
			return "serviceunavailable";
		}
		catch(Exception exception) {
			modelMap.addAttribute("loginMessage", "Invalid Credentials");
			return "login";
		}
		httpSession.setAttribute("token", "Bearer " + response.getJwt());
		return "redirect:/dashboard";
	}
	
	public String getDashboard(HttpSession httpSession,ModelMap modelMap) {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			return "dashboard";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
	public String getQuoteSearch(HttpSession httpSession,ModelMap modelMap) {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			return "getquoteform";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
	public String getQuoteResult(HttpSession httpSession,ModelMap modelMap,CustomerDetails customerDetails) {
		String jwt = (String) httpSession.getAttribute("token");
		Quotes quotes;
		if(jwt != null) {
			try {
				 quotes = quotesProxy.getQuote(customerDetails, jwt).getBody();
			}
			catch(feign.RetryableException exception) 
			{
				return "serviceunavailable";
			}
			catch (Exception exception) 
			{
				exception.printStackTrace();
				modelMap.addAttribute("loginMessage", "Please login!");
				return "/login";
			}
			modelMap.addAttribute("quote", quotes);
			return "quoteresult";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
	
	public String getSubmitQuoteForm(HttpSession httpSession, ModelMap modelMap) {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			return "submitquote";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
	
	public String submitQuote(HttpSession httpSession,ModelMap modelMap,CustomerPersonalDetails customerPersonalDetails) {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			try {
				quotesProxy.saveQuote(customerPersonalDetails, jwt);
			}
			catch(feign.RetryableException exception) 
			{
				return "serviceunavailable";
			}
			return "redirect:/dashboard";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
}