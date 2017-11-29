package secondProject.account;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Account createAccount(Account dto){
		String username = dto.getEmail();
		if(accountRepository.findByEmail(username)!=null){
			throw new UserDuplicatedException(username);
		}
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		return accountRepository.save(dto);		
	}

	public Account passwordUpdate(Long id, AccountDTO.PasswordUpdate dto) {
		Account account = getAccount(id);
		
		account.setPassword(passwordEncoder.encode(dto.getNewPassword()));
		return accountRepository.save(account);
	}
	
	public Account getAccount(Long id){
		Account account = accountRepository.findOne(id);
		if(account == null){
			try {
				throw new AccountNotFoundException();
			} catch (AccountNotFoundException e) {
				e.printStackTrace();
			}
		}
		return account;
	}
}
