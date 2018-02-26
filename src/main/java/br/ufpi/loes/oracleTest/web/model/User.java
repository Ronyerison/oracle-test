/**
 * 
 */
package br.ufpi.loes.oracleTest.web.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
//	@NotNull(message = "required")
//	@Length(min = 3, max = 20)
//	@Pattern(regexp = "[a-zA-Z0-9_]+")
	private String login;

//	@NotNull
//	@Length(min = 6)
	private String password;
	
	private String recoveryCode;
	
//	@NotNull
//	@Email
	private String email;

//	@NotNull
//	@Length(min = 3, max = 100)
	private String name;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE, targetEntity=Application.class)
	private List<Application> applications;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the recoveryCode
	 */
	public String getRecoveryCode() {
		return recoveryCode;
	}

	/**
	 * @param recoveryCode the recoveryCode to set
	 */
	public void setRecoveryCode(String recoveryCode) {
		this.recoveryCode = recoveryCode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", recoveryCode=" + recoveryCode
				+ ", email=" + email + ", name=" + name + "]";
	}

}