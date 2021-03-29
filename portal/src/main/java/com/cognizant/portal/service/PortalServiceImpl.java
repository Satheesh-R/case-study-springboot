package com.cognizant.portal.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.cognizant.portal.feign.AuthenticationProxy;
import com.cognizant.portal.feign.QuotesProxy;
import com.cognizant.portal.model.AuthenticationRequest;
import com.cognizant.portal.model.AuthenticationResponse;
import com.cognizant.portal.model.CustomerDetails;
import com.cognizant.portal.model.CustomerDetailsProxy;
import com.cognizant.portal.model.CustomerPersonalDetails;
import com.cognizant.portal.model.CustomerRegistrationDetails;
import com.cognizant.portal.model.Message;
import com.cognizant.portal.model.QuoteDetails;
import com.cognizant.portal.model.Quotes;

@Service
public class PortalServiceImpl {
	
	@Autowired
	private AuthenticationProxy authenticationProxy;
	@Autowired
	private QuotesProxy quotesProxy;
	@Value("${upload.path}")
	private String uploadPath;
	private AuthenticationResponse response;
	
	public String getSignin(HttpSession httpSession) {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			return "redirect:/dashboard";
		}
		else {
			return "login";
		}
	}
	
	public String register(HttpSession httpSession, ModelMap modelMap) {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			return "dashboard";
		}
		else {
			return "register";
		}
	}
	
	public String registerCustomer(HttpSession httpSession,ModelMap modelMap,
			CustomerRegistrationDetails customerRegistrationDetails) {
		String jwt = (String) httpSession.getAttribute("token");
		Message message;
		if(jwt != null) {
			return "dashboard";
		}
		else {
			try {
				message = authenticationProxy.registerCustomer(customerRegistrationDetails).getBody();
			}
			catch(feign.RetryableException exception) 
			{
				return "serviceunavailable";
			}
			if(message.getStatus().equals(HttpStatus.NOT_ACCEPTABLE)) {
				modelMap.addAttribute("userMessage","Username is already taken");
				return "register";
			}
			AuthenticationRequest authenticationRequest = new AuthenticationRequest(
					customerRegistrationDetails.getCustomerId(),customerRegistrationDetails.getPassword());
			return authenticate(authenticationRequest, httpSession, modelMap);
		}
	}
	
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
			modelMap.addAttribute("age", customerDetails.getAge());
			return "quoteresult";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
	
	public String getSubmitQuoteForm(HttpSession httpSession, ModelMap modelMap,Quotes quotes) {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			modelMap.addAttribute("quote",quotes);
			return "submitquote";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
	
	public String submitQuote(HttpSession httpSession,ModelMap modelMap,CustomerDetailsProxy customerDetailsProxy) throws IOException {
		String jwt = (String) httpSession.getAttribute("token");
		if(jwt != null) {
			try {
				Path path = Paths.get(uploadPath);
				Files.copy(customerDetailsProxy.getFileData().getInputStream(), 
						path.resolve(customerDetailsProxy.getFileData().getOriginalFilename()));
				CustomerPersonalDetails customerPersonalDetails = new CustomerPersonalDetails(
						customerDetailsProxy.getFirstname(), customerDetailsProxy.getLastname(), 
						customerDetailsProxy.getGender(), customerDetailsProxy.getAge(), 
						customerDetailsProxy.getEmailid(), customerDetailsProxy.getMobileNumber(), 
						customerDetailsProxy.getQuoteAmount(), customerDetailsProxy.getFileData().getBytes());
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
	
	public String retrieveQuote(HttpSession httpSession, ModelMap modelMap) {
		String jwt = (String) httpSession.getAttribute("token");
		List<QuoteDetails> quoteDetailsList;
		if(jwt != null) {
			try {
				quoteDetailsList = quotesProxy.getAllQuotes(jwt).getBody();
			}
			catch(feign.RetryableException exception) 
			{
				return "serviceunavailable";
			}
			modelMap.addAttribute("quotesDetailsList",quoteDetailsList);
			return "quotesdetails";
		}
		else {
			modelMap.addAttribute("loginMessage", "Please login!");
			return "redirect:/login";
		}
	}
}