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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Ronyerison
 *
 */
@Entity
public class Application implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String url;
	private boolean hasTestUser;
	private String loginUser;
	private String passwordUser;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="owner_id")
	private User owner;
	
	@OneToMany(mappedBy="application", cascade=CascadeType.ALL)
	private List<Action> actions;
	
	@OneToMany(mappedBy="application", cascade=CascadeType.ALL)
	private List<MachineLearningReport> reports;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean getHasTestUser() {
		return hasTestUser;
	}
	public void setHasTestUser(boolean hasTestUser) {
		this.hasTestUser = hasTestUser;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<Action> getActions() {
		return actions;
	}
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	public List<MachineLearningReport> getReports() {
		return reports;
	}
	public void setReports(List<MachineLearningReport> reports) {
		this.reports = reports;
	}
	@Override
	public String toString() {
		return "Application [id=" + id + ", name=" + name + ", url=" + url + ", owner=" + owner + "]";
	}
	
	

}
