package com.aloa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aloa.security.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf();
		
		
		http
		.authorizeRequests()   
			.antMatchers(HttpMethod.GET, "/accounts/**").hasRole("USER")
			.antMatchers(HttpMethod.PUT, "/accounts/**").hasRole("USER")
			.antMatchers(HttpMethod.PATCH, "/accounts/**").hasRole("USER")
			.antMatchers(HttpMethod.DELETE, "/accounts/**").hasRole("USER") 
			.antMatchers("/manager/**").hasRole("ADMIN")
			.antMatchers("/**").permitAll()
			.and()
		.formLogin()
			.loginPage("/login").permitAll() 
			.and()
		.logout()
			.logoutSuccessUrl("/");
	}
}
