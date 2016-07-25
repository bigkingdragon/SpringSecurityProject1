package com.netsong7.test1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping(value = "addMember")
	public void addMemberGet(MemberVO vo){
		//dao.addUser(vo);
	}
	
	@RequestMapping(value = "addMember", method=RequestMethod.POST)
	public String addMemberPost(MemberVO vo){
		logger.info("username : " + vo.getUsername());
		dao.addUser(vo);
		return "home";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login(MemberVO vo, HttpSession session) throws IOException {
		logger.info("Welcome login! {}", session.getId());
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(vo.getUserid(), vo.getUserpw(), roles);
		Authentication auth = result;
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@RequestMapping(value = "login_success", method = RequestMethod.GET)
	public void login_success(MemberVO vo) {
		//MemberVO member = dao.login(vo);
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
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "users/welcome";
	}


	@RequestMapping(value = "/editAccount", method = RequestMethod.GET)
	public String editAccount(HttpServletRequest req, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("USER_KEY");

		if (user == null) {
			// 로그인 후 다시 돌아오기 위해
			String url = req.getServletPath();
			String query = req.getQueryString();
			if (query != null)
				url += "?" + query;
			// 로그인 페이지로 리다이렉트
			url = URLEncoder.encode(url, "UTF-8");

			return "redirect:/users/login?url=" + url;
		}

		return "users/editAccount";
	}

	@RequestMapping(value = "/changePasswd", method = RequestMethod.GET)
	public String changePasswd(HttpServletRequest req, HttpSession session) throws Exception {
		User user = (User) session.getAttribute("USER_KEY");

		if (user == null) {
			// 로그인 후 다시 돌아오기 위해
			String url = req.getServletPath();
			String query = req.getQueryString();
			if (query != null)
				url += "?" + query;
			// 로그인 페이지로 리다이렉트
			url = URLEncoder.encode(url, "UTF-8");
			return "redirect:/users/login?url=" + url;
		}

		return "users/changePasswd";
	}
	
	/*
	@RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
	public String changePasswd(String currentPasswd, String newPasswd, HttpSession session) {
		String email = ((User) session.getAttribute(WebConstants.USER_KEY)).getUserid();

		int check = dao.changePasswd(currentPasswd, newPasswd, email);
		if (check < 1) {
			throw new Exception(WebConstants.AUTHENTICATION_FAILED);
		}

		return "redirect:/users/changePasswd_confirm";

	}

	@RequestMapping(value = "/changePasswd_confirm", method = RequestMethod.GET)
	public String changePasswd_confirm() {
		return "users/changePasswd_confirm";
	}

	@RequestMapping(value = "/bye", method = RequestMethod.GET)
	public String bye(HttpServletRequest req, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(WebConstants.USER_KEY);

		if (user == null) {
			// 로그인 후 다시 돌아오기 위해
			String url = req.getServletPath();
			String query = req.getQueryString();
			if (query != null)
				url += "?" + query;
			// 로그인 페이지로 리다이렉트
			url = URLEncoder.encode(url, "UTF-8");

			return "redirect:/users/login?url=" + url;
		}

		return "users/bye";
	}

	@RequestMapping(value = "/bye", method = RequestMethod.POST)
	public String bye(String email, String passwd, HttpSession session) {
		User user = (User) session.getAttribute(WebConstants.USER_KEY);

		if (user == null || !user.getEmail().equals(email)) {
			throw new AuthenticationException(WebConstants.AUTHENTICATION_FAILED);
		}

		user = dao.login(email, passwd);
		dao.bye(user);
		session.removeAttribute(WebConstants.USER_KEY);

		return "redirect:/users/bye_confirm";

	}

	@RequestMapping(value = "/bye_confirm", method = RequestMethod.GET)
	public String bye_confirm() {

		return "users/bye_confirm";
	}
	*/
}
