package pojoClassConcept;

/**
 * This is the POJO class which can neither be child class and nor be the Parent
 * Class. It has to be the separate independent class
 */
public class CredentialsBookerPojo {

	private String username;
	private String password;

	public CredentialsBookerPojo(String username, String password) {
		this.username = username;
		this.password = password;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
