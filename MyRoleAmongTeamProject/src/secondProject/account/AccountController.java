package secondProject.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aloa.additinfo.AccountAdditInfo;
import com.aloa.additinfo.AccountAdditInfoRepository;
import com.aloa.security.UserDetailsImpl;


@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountAdditInfoRepository accountAdditInfoRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/agreeJoin", method = RequestMethod.GET)
	public ModelAndView agreeJoin(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/agreeJoin");
		
		return mav;
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm(){
		return "member/writeForm";
	}
	
	@RequestMapping(value="/login")
	public void login(){}
	
	@RequestMapping(value="/admin")
	public void admin(){}
	
	@RequestMapping(value="/accounts", method=RequestMethod.POST)
	public String createAccounts(Account account){
		accountService.createAccount(account);
		AccountAdditInfo accountAddit = new AccountAdditInfo();
		accountAddit.setId(account.getId());
		accountAddit.setGender(0);
		accountAdditInfoRepository.save(accountAddit);
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(account);
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
	
	@RequestMapping(value="/accounts/userInfo", method=RequestMethod.GET)
	public String editAccount(Model model){
		model.addAttribute("display", "./template/editUserInfo.jsp");
		return "member/userInfo";
	}
	
	@RequestMapping(value="/accounts/widthdrawalUser", method=RequestMethod.GET)
	public String widthdrawalUser(Model model){
		model.addAttribute("display", "./template/widthdrawalUser.jsp");
		return "member/userInfo";
	}
	
	@RequestMapping(value="/accounts/message", method=RequestMethod.GET)
	public String message(Model model){
		model.addAttribute("display", "./template/message.jsp");
		return "member/userInfo";
	}
	
	@RequestMapping(value="/accounts/reservation", method=RequestMethod.GET)
	public String reservation(Model model){
		model.addAttribute("display", "./template/reservation.jsp");
		return "member/userInfo";
	}
}












