package backend.api.project.common.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import backend.api.project.login.dto.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwtService {
	@Value("${jwt.key}")
	private String key;
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	public String create(final Member member) throws Exception {
		log.info("JWTService token create.");
		final JwtBuilder builder = Jwts.builder();
		
		final String jwt = builder.setHeaderParam("typ", "JWT")
			.setSubject("shoppingMall")
			.setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))
			.claim("Member", member)
			.claim("second", "others")
			.signWith(SignatureAlgorithm.HS256, key.getBytes())
			.compact();
		
		log.info("JWTService token create = {}", jwt);
		
		return jwt;
	}
	
	public void checkValid(final String jwt) throws Exception {
		Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwt);
	}
	
	public Map<String, Object> get(final String jwt) throws Exception {
		Jws<Claims> claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwt);
		
		return claims.getBody();
		
	}

}
