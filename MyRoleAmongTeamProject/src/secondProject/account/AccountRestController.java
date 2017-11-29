package secondProject.account;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aloa.additinfo.AccountAdditInfoRepository;


@RestController
public class AccountRestController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountAdditInfoRepository accountAdditInfoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/inspect", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public boolean inspect(@RequestParam String email){
		System.out.println("?ùº?ã® ?ò§?Éê");
		Account account = accountRepository.findByEmail(email);
		if(account != null){
			return false;
		}
		return true;
	}

	/*@RequestMapping(value="/accounts/{id}", method=RequestMethod.GET)
	public AccountDTO.GetAccount getAccount(@PathVariable Long id){
		Account account = accountRepository.findOne(id);
		return modelMapper.map(account, AccountDTO.GetAccount.class);
	}*/
	
	@RequestMapping(value="/accounts/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<Account> updatePassword(@PathVariable Long id,
								  @RequestBody AccountDTO.PasswordUpdate dto){
		Account account = accountRepository.findOne(id);
		String password = dto.getPassword();
		if(!passwordEncoder.matches(password, account.getPassword())){
			return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
		}
		account = accountService.passwordUpdate(id, dto);
		return new ResponseEntity<Account>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/accounts/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Account> deleteGetAccount(@PathVariable Long id,
												 @RequestBody String widthpassword){
		String[] array;
		array = widthpassword.split("=");
		Account account = accountRepository.findOne(id);
		if(!passwordEncoder.matches(array[1], account.getPassword())){
			return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
		}
		accountRepository.delete(id);
		return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
	}
}
