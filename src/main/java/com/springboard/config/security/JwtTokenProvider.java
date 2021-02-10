package com.springboard.config.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	@Value("spring.jwt.secret")
	private String secretKey;
	private long tokenValidMillisecond = 1000L * 60 * 60;
	private final UserDetailsService UserDetailsService;
	
	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String createToken(String email,List<String> roles) {
		// claim == 사용자 정보
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("roles",roles);
		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime()+tokenValidMillisecond))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	// JWT 토큰에서 인증정보 조회하기  
	 public UsernamePasswordAuthenticationToken getAuthentication(String token) {
	        UserDetails userDetails = UserDetailsService.loadUserByUsername(this.getUserPk(token));
	        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	    }

	    // 토큰에서 회원 정보 추출
	    public String getUserPk(String token) {
	        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	    }

	    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
	    public String resolveToken(HttpServletRequest request) {
	        return request.getHeader("X-AUTH-TOKEN");
	    }

	    // 토큰의 유효성 + 만료일자 확인
	    public boolean validateToken(String jwtToken) {
	        try {
	            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
	            return !claims.getBody().getExpiration().before(new Date());
	        } catch (Exception e) {
	            return false;
	        }
	    }
	}