package com.springboard.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboard.config.security.JwtTokenProvider;
import com.springboard.domain.User;
import com.springboard.persistence.UserRepository;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;

@RequiredArgsConstructor
@Service
public class CustomUserDetailServiceimpl implements UserDetailsService {
	
	@Autowired
	public UserRepository UserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return UserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
	}
	
	public String signup(JSONObject user, PasswordEncoder passwordEncoder) {
		UserRepository.save(User.builder()
                .email(user.get("email").toString())
                .password(passwordEncoder.encode((CharSequence)user.get("password")))
                .roles(Collections.singletonList("USER")) // 최초 가입시 USER 로 설정
                .build());
		return "Created";
	}
	
	public String login(@RequestBody JSONObject user, JwtTokenProvider jwtTokenProvider) {
		User loginuser = UserRepository.findByEmail(user.get("email").toString()).orElse(null);
		return jwtTokenProvider.createToken(loginuser.getUsername(), loginuser.getRoles());
	}
	
	public String leave(Long uid) {
		UserRepository.deleteById(uid);
		return "Leaved";
	}
	
	public User setNickname(Long uid, String nickname) {
		User user = UserRepository.findById(uid).get();
		user.setNickname(nickname);
		UserRepository.save(user);
		System.out.println(user.getNickname());
		return user;
	}
}
