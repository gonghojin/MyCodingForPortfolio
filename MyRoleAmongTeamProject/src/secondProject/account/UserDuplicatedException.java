package secondProject.account;

public class UserDuplicatedException extends RuntimeException {
	private String username;
	
	public UserDuplicatedException(String username) {
		this.username = username;
	}

	public String getEmail() {
		return username;
	}

	public void setEmail(String username) {
		this.username = username;
	}

}
