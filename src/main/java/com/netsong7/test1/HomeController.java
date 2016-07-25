package com.netsong7.test1;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}
	
	/*
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login(HttpSession session) throws IOException {
		logger.info("Welcome login! {}", session.getId());
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpSession session) {
		CustomUserDetails userDetails = (CustomUserDetails) session.getAttribute("userLoginInfo");
		logger.info("Welcome logout! {}, {}", session.getId(), userDetails.getUsername());
		logger.info("현재 권한 : " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		session.invalidate();
	}

	@RequestMapping(value = "login_success", method = RequestMethod.GET)
	public void login_success(HttpSession session) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();

		logger.info("Welcome login_success! {}, {}", session.getId(),
				userDetails.getUsername() + "/" + userDetails.getPassword());
		session.setAttribute("userLoginInfo", userDetails);
	}

	@RequestMapping(value = "login_duplicate", method = RequestMethod.GET)
	public void login_duplicate() {
		logger.info("Welcome login_duplicate!");
	}

	@RequestMapping(value = "page1", method = RequestMethod.GET)
	public void page1(HttpSession session) {
		CustomUserDetails userDetails = (CustomUserDetails) session.getAttribute("userLoginInfo");
		logger.info("Welcome logout! {}, {}", session.getId(), userDetails.getUsername());

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "admin/admin", method = RequestMethod.GET)
	public void admin(){
		
	}
	*/
}
