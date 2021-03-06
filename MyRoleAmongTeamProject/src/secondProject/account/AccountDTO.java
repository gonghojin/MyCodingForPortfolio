package secondProject.account;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class AccountDTO {
	public static class PasswordUpdate {
		private String password;
		private String newPassword;
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNewPassword() {
			return newPassword;
		}
		public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
		}
	}

	public static class GetAccount {
		private String email;
		private String username;
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
	}

	public static class Create{
		@NotBlank(message="email? ?? ₯??­??€")
		@Size(min = 10, max=35)
		@Email(message="?¬λ°λ₯Έ email ???΄ ????€")
		private String email;
		
		@NotBlank(message="λΉλ?λ²νΈλ₯? ?? ₯??­??€")
		@Size(min = 8, max=20, message="8~20? ?΄?΄? ?¨?€??λ₯? ?¬?©?΄ μ£ΌμΈ?.")
		private String password;
		
		@NotBlank(message="?΄λ¦μ ?? ₯??­??€")
		@Size(max=20, message="20? ?΄?΄λ‘? ?? ₯??­??€.")
		private String username;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}		
	}
}
