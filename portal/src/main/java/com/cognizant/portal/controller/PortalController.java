package com.cognizant.portal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.portal.model.AuthenticationRequest;
import com.cognizant.portal.model.CustomerDetails;
import com.cognizant.portal.model.CustomerDetailsProxy;
import com.cognizant.portal.model.CustomerRegistrationDetails;
import com.cognizant.portal.model.Quotes;
import com.cognizant.portal.service.PortalServiceImpl;

@Controller
public class PortalController {
	
	@Autowired
	private PortalServiceImpl portalServiceImpl;
	
	@Cacheable(value = "countries")
	@ModelAttribute("countryList")
	public Map<String, String> getCountries(){
		Map<String,String> countries = new HashMap<String, String>();
		countries.put("IND","INDIA");
		countries.put("ESP","SPAIN");
		countries.put("USA","UNITED STATES OF AMERICA");
		countries.put("ARG","ARGENTINA");
		return countries;
	}
	@GetMapping("/login")
	public String getSignin(HttpSession httpSession) {
		return portalServiceImpl.getSignin(httpSession);
	}
	
	@GetMapping("/")
	public String getLogin() {
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String getRegister(HttpSession httpSession,ModelMap modelMap,
			@ModelAttribute("customerRegistrationDetails") CustomerRegistrationDetails customerRegistrationDetails,BindingResult bindingResult) {
		return portalServiceImpl.register(httpSession, modelMap);
	}
	
	@PostMapping("/registerCustomer")
	public String registerCustomer(HttpSession httpSession,ModelMap modelMap,
			@ModelAttribute("customerRegistrationDetails") CustomerRegistrationDetails customerRegistrationDetails,BindingResult bindingResult) {
		System.out.println(customerRegistrationDetails.getCountry());
		return portalServiceImpl.registerCustomer(httpSession, modelMap, customerRegistrationDetails);
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
	
	@PostMapping("/submitNewQuote")
	public String getSubmitQuoteForm(HttpSession httpSession, ModelMap modelMap,
			@ModelAttribute Quotes quotes) {
		return portalServiceImpl.getSubmitQuoteForm(httpSession, modelMap, quotes);
	}
	
	@PostMapping("/submitQuote")
	public String submitQuote(HttpSession httpSession, ModelMap modelMap,
			@ModelAttribute CustomerDetailsProxy customerDetailsProxy) throws IOException {
		return portalServiceImpl.submitQuote(httpSession, modelMap, customerDetailsProxy);
	}
	
	@GetMapping("/retrieveQuote")
	public String retrieveQuote(HttpSession httpSession, ModelMap modelMap) {
		return portalServiceImpl.retrieveQuote(httpSession, modelMap);
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.setAttribute("token", null);
		httpSession.invalidate();
		return "redirect:/login";
	}
}