package kr.ac.huh.controller;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;


import kr.ac.huh.service.KakaoLogin;
import kr.ac.huh.service.UserVO;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(
			@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout, 
			Model model) {
		
		if(a==0)
			auth_init();
		updatedAuthorities.clear();
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
		System.out.println(" 111");
		
		
		
		
		if (error != null) {
			model.addAttribute("errorMsg", "Invalid username and password");
		}
		if (logout != null) {
			model.addAttribute("logoutMsg", "You have been logged out successfully");
		}
		
		
		
		return "login";
	}
	
	
	UserVO vo;
	Authentication auth;
	List<GrantedAuthority> updatedAuthorities;
	int a=0;
	
	@RequestMapping(value = "/kakaologin" , produces = "application/json", method = {RequestMethod.GET, RequestMethod.POST})
	public String kakaoLogin(@RequestParam("code") String code , HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{

	  JsonNode token = KakaoLogin.getAccessToken(code);

	  JsonNode profile = KakaoLogin.getKakaoUserInfo(token.path("access_token").toString());
	  System.out.println(profile);
	  
	  vo = KakaoLogin.changeData(profile);
	  vo.setUser_snsId("k"+vo.getUser_snsId());

	  System.out.println(session);
	  session.setAttribute("login", vo);
	  System.out.println(vo.toString());

	 // vo = service.kakaoLogin(vo);  
	  
	  System.out.println(vo.getUser_name());
	 // System.out.println((String)request.getUserPrincipal().getName());
	 // model.addAttribute("name", vo.getUser_name());
	  

	  
	  /*//alert 창 띄우기
	  
	  response.setContentType("text/html; charset=utf-8");
	  PrintWriter out = response.getWriter();
	  out.println("<script language='javascript'>");
	  out.println("alert('로그인 되었습니다');");
	  out.println("</script>");
	  out.flush();*/
	  
	  
	  //권한
	  
	  if(a==0)
		  auth_init();
	  updatedAuthorities.clear();
	  updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER")); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
	  Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
	  SecurityContextHolder.getContext().setAuthentication(newAuth);
	  System.out.println(newAuth.getAuthorities().toString());
	  
	  
	  
	  
	  /*Authentication auth = (Authentication) request.getUserPrincipal();
	  if(newAuth == null) {
		  System.out.print("a");
		  
		  auth.setAuthenticated(true);
		  SecurityContextHolder.getContext().setAuthentication(auth);
	  }
	  else {
		  System.out.print("b");
	  }*/
	  
	  
	  
	  return "home";
	}
	
	public void auth_init() {
		auth = SecurityContextHolder.getContext().getAuthentication();
		updatedAuthorities = new ArrayList<>(auth.getAuthorities());
		a++;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws Exception{

		
		
		  vo.setUser_name(null);
		  vo.setUser_profileImagePath(null);
		  vo.setUser_snsId(null);
		  
		  
		  System.out.println(session);
		  session.setAttribute("login", vo);
		  session.invalidate(); //세션을 끝내야 함
		  System.out.println(vo.toString());
		 


		  
		/*//alert 창 띄우기
		  
		  response.setContentType("text/html; charset=utf-8");
		  PrintWriter out = response.getWriter();
		  out.println("<script language='javascript'>");
		  out.println("alert('로그아웃 되었습니다');");
		  out.println("</script>");
		  out.flush();*/
		  
		  
		  //권한
		  
		 // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 // List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
		  

		  updatedAuthorities.clear();
		  updatedAuthorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS")); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]
		  Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
		  SecurityContextHolder.getContext().setAuthentication(newAuth);
		  System.out.println(newAuth.getAuthorities().toString());
		
		return "home";
	}
	
}
