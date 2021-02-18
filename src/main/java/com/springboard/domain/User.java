package com.springboard.domain;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.springboard.service.BoardService;

import java.util.ArrayList;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "user")
@EqualsAndHashCode(of="uid")
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uid;
	@Column(unique = true)
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String nickname;
	
	@JsonIgnore
	public String username;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles = new ArrayList<>();
	
	@Override
	@JsonIgnore // 리턴시 이 필드는 표시안함 (정확히는 JSON으로 변환 안하는것)
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return this.roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}
	
	
	
	@Override
    public String getUsername() {
        return this.email;
    }
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String isAdmin() {
		System.out.println(this.roles.get(0));
		return this.roles.get(0);
	}
}
