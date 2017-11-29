package org.hojin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hojin.model.User;
import org.hojin.model.UserProfile;
import org.hojin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
	
	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	 
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		
		User user = userService.findBySSO(ssoId);
		logger.info("User : {}", user);
		
		if(user == null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), true, true
				, true, true, getGrantedAuthorities(user));

}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfile userProfile: user.getUserProfiles()){
			logger.info("UserProfile : {}", userProfile);
			 authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		logger.info("authorities : {}", authorities);
        return authorities;
		
	}
}