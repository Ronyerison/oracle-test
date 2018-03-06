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
	private Boolean captureActions;
	private String captureCode;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="owner_id")
	private User owner;
	
	@OneToMany(mappedBy="application", cascade=CascadeType.ALL)
	private List<Action> actions;
	
	@OneToMany(mappedBy="application", cascade=CascadeType.ALL)
	private List<MachineLearningReport> reports;
	
	@OneToMany(mappedBy="application", cascade=CascadeType.ALL)
	private List<Simulation> simulations;
	
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
	public Boolean getCaptureActions() {
		return captureActions;
	}
	public void setCaptureActions(Boolean captureActions) {
		this.captureActions = captureActions;
	}
	public String getCaptureCode() {
		return captureCode;
	}
	public void setCaptureCode(String captureCode) {
		this.captureCode = captureCode;
	}
	public List<Simulation> getSimulations() {
		return simulations;
	}
	public void setSimulations(List<Simulation> simulations) {
		this.simulations = simulations;
	}
	

}
