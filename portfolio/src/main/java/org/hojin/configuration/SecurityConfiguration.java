package org.hojin.configuration;

import org.hojin.security.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	PersistentTokenRepository tokenRepository;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
			//csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
		
        
		http.csrf().disable().authorizeRequests()
		.antMatchers( "/user/", "/user/list").access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
		.antMatchers("/user/delete-user-*").access("hasRole('ADMIN')")
		.antMatchers("/user/edit-user-*").access("hasRole('ADMIN') or hasRole('DBA')")
		.antMatchers(HttpMethod.OPTIONS,"/board/*").permitAll()
		.antMatchers(HttpMethod.OPTIONS,"/test/*").permitAll()
		.and()
		.formLogin().loginPage("/user/login")
		.loginProcessingUrl("/user/login").usernameParameter("ssoId").passwordParameter("password").successHandler(successHandler())
		.and()
		.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
		.tokenValiditySeconds(86400).and().exceptionHandling().accessDeniedPage("/user/Access_Denied");
					
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices(){
		PersistentTokenBasedRememberMeServices tokenBasedservice = 
		new PersistentTokenBasedRememberMeServices("remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}
	
	//login으로 인증 시 인증 전 URL 페이지 기억
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler("/defaultUrl");
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationTrustResolver getAuthenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

}
/*
 * Since we are storing the credentials in database, 
 * configuring DaoAuthenticationProvider with UserDetailsService would come handy.
 * Additionally, in order to encrypt the password in database,
 * we have chosen BCryptPasswordEncoder. Moreover, since we will also provide RememberMe functionality,
 *  keeping track of token-data in database, we configured a PersistentTokenRepository implementation./
 */