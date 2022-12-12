package fr.tse.startuppoc.project.utils;

public class LoginUser {

	private String login;
	private String password;
	
	public LoginUser(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public LoginUser() {
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
