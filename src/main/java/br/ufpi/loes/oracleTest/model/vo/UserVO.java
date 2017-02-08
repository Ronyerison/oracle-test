package br.ufpi.loes.oracleTest.model.vo;

import br.ufpi.loes.oracleTest.model.User;

public class UserVO {
	private String login;
	private String name;
	private String email;
	
	public UserVO(User user) {
		this(user.getLogin(), user.getName(), user.getEmail());
	}
	
	public UserVO(String login, String name, String email) {
		this.login = login;
		this.name = name;
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserVO [login=" + login + ", name=" + name + ", email=" + email
				+ "]";
	}
	
}
