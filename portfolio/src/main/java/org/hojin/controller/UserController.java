package org.hojin.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hojin.model.User;
import org.hojin.model.UserProfile;
import org.hojin.service.UserProfileService;
import org.hojin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/user/*")
@SessionAttributes("roles")//반드시 모델 객체에 저장된 객체만 sessionAttributes에 저장: model.addAttribute("data1", "");
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	//RememberMe 기능 사용 시 이걸로 로그아웃
	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	//login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request){
		if(isCuurntAuthenticationAnonymous()){
			String referrer = request.getHeader("Referer");
			request.getSession().setAttribute("prevPage", referrer);
			
			return "/user/login";
		}else{
			//If users is already logged-in and tries to goto login page again, will be redirected to list page.
			return "redirect:/user/list";
		}
	}
	
	private boolean isCuurntAuthenticationAnonymous() {
		 final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 return authenticationTrustResolver.isAnonymous(authentication);
	}
	//logout
	//Toggle the handlers if you are RememberMe functionality is useless in your app.
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null){
		//new SecurityContextLogoutHandler().logout(request, response, auth);
			
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/user/login?logout";
	}
	
	
	//all existing users.
	@RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
	public String listUsers(ModelMap model){
		
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "/user/userslist";
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newUser(ModelMap model){
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "/user/registration";
	}
	
	/*
     * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
     * and applying it on field [sso] of Model class [User].
     * 
     * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
     * framework as well while still using internationalized messages.
     * 
     */

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model){
		if(result.hasErrors()){
			return "/user/registration";
		}
		
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError = new FieldError("user", "ssoId", messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
			result.addError(ssoError);
			return "/user/registration";
		}
		
		userService.saveUser(user); 
		
		model.addAttribute("success","User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "/user/registrationsuccess";
		
	}
	
	//edit user - GET
	@RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model){
		User user = userService.findBySSO(ssoId);
		
		model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        
        return "/user/registration";

	}
	/**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
	@RequestMapping(value = "/edit-user-{ssoId}", method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId){
		
		if(result.hasErrors()){
			return "/user/registration";
		}
		
		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/

		userService.updateUser(user);
		
		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "/user/registrationsuccess";

	}
	
	@RequestMapping(value = "/delete-user-{ssoId}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId){
		userService.deleteUserBySSO(ssoId);
		
		return "redirect:/user/list";
	}
	
	/**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }
 

	/**
     * This method will provide UserProfile list to views
     */

	@ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
    }
	
	/**
     * This method returns the principal[user-name] of logged-in user.
     */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(principal instanceof UserDetails){
			userName = ((UserDetails) principal).getUsername();
		}else{
			userName = principal.toString();
		}
		
		return userName;
	}
}
