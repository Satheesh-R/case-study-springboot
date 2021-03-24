package com.cognizant.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.portal.model.AuthenticationRequest;
import com.cognizant.portal.model.CustomerDetails;
import com.cognizant.portal.model.CustomerPersonalDetails;
import com.cognizant.portal.service.PortalServiceImpl;

@Controller
public class PortalController {
	
	@Autowired
	private PortalServiceImpl portalServiceImpl;
	
	@GetMapping("/")
	public String getLogin() {
		return "login";
	}
	
	@PostMapping("/authenticate")
	public String authenticate(@ModelAttribute AuthenticationRequest authenticationRequest,HttpSession httpSession,ModelMap modelMap) {
		return portalServiceImpl.authenticate(authenticationRequest, httpSession, modelMap);  
	}
	
	@GetMapping("/dashboard")
	public String getDashboard(HttpSession httpSession,ModelMap modelMap) {
		return portalServiceImpl.getDashboard(httpSession, modelMap);
	}
	
	@GetMapping("/getQuote")
	public String getQuoteSearch(HttpSession httpSession,ModelMap modelMap) {
		return portalServiceImpl.getQuoteSearch(httpSession, modelMap);
	}
	
	@GetMapping("/quoteResult")
	public String quoteResult(HttpSession httpSession, ModelMap modelMap, 
			@ModelAttribute CustomerDetails customerDetails) {
		return portalServiceImpl.getQuoteResult(httpSession, modelMap,customerDetails);
	}
	
	@GetMapping("/submitNewQuote")
	public String getSubmitQuoteForm(HttpSession httpSession, ModelMap modelMap) {
		return portalServiceImpl.getSubmitQuoteForm(httpSession, modelMap);
	}
	
	@PostMapping("/submitQuote")
	public String submitQuote(HttpSession httpSession, ModelMap modelMap,
			@ModelAttribute CustomerPersonalDetails customerPersonalDetails) {
		return portalServiceImpl.submitQuote(httpSession, modelMap, customerPersonalDetails);
	}
}