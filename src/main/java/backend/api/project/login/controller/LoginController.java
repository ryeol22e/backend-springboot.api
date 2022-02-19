package backend.api.project.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.api.project.common.service.JwtService;
import backend.api.project.login.dto.Member;
import backend.api.project.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/logins")
public class LoginController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginMember(@RequestBody Member member, HttpServletResponse response) throws Exception {
		log.info("login start...");
		Map<String, Object> result = new HashMap<>();
		Member getMember = loginService.getMemberInfo(member);
		String token = jwtService.create(getMember);
		
		if(token!=null && token.length()>0) {
			log.info("token:::::::::: {}", token);
			response.setHeader("Authorization", token);
			
			result.put("email", getMember.getEmail());
			result.put("name", getMember.getName());
		}
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/info")
	public ResponseEntity<?> checkLoginMember(HttpServletRequest request, Member member) throws Exception {
		String jwt = request.getHeader("Authorization");
		Map<String, Object> result = new HashMap<>();
		log.info("request jwt token = {}", jwt);
		
		jwtService.get(jwt);
		result.put("Authorization", jwt);
		result.put("memeber", member);
		
		return ResponseEntity.ok(result);
		
	}
}
