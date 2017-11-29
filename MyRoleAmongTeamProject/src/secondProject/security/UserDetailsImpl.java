package com.aloa.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.aloa.account.Account;

public class UserDetailsImpl extends User{
	
	private Long id;
	private String fullName;
	private String email;
	
	private String state;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName(){
		return fullName;
	}
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public UserDetailsImpl(Account account) {
		super(account.getEmail(), account.getPassword(), authorities(account));
		this.id = account.getId();
		this.fullName = account.getUsername();
		this.email = account.getEmail();
		this.state = account.getStatus();
	}
	
	private static Collection<? extends GrantedAuthority> authorities(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(account.getAdmin()==1){
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return authorities;
	}
}