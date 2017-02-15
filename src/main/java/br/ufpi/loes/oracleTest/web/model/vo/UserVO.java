package br.ufpi.loes.oracleTest.web.model.vo;

import br.ufpi.loes.oracleTest.web.model.User;

public class UserVO {
	private Long id;
	private String login;
	private String name;
	private String email;
	
	public UserVO(User user) {
		this(user.getId(), user.getLogin(), user.getName(), user.getEmail());
	}
	
	public UserVO(Long id, String login, String name, String email) {
		this.id = id;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", login=" + login + ", name=" + name
				+ ", email=" + email + "]";
	}
	
}
