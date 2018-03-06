/**
 * 
 */
package br.ufpi.loes.oracleTest.web.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Rony
 *
 */
@Entity
public class Simulation implements Serializable{


	private static final long serialVersionUID = -3154033064724283038L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar simulationDate;
	
	@ManyToOne
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private MachineLearningReport report;
	
	@ManyToOne
	private Application application;
	
	public Simulation() {
	}

	public Simulation(User user, MachineLearningReport report, Application application) {
		super();
		this.user = user;
		this.report = report;
		this.application = application;
		this.simulationDate = Calendar.getInstance();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getSimulationDate() {
		return simulationDate;
	}

	public void setSimulationDate(Calendar simulationDate) {
		this.simulationDate = simulationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MachineLearningReport getReport() {
		return report;
	}

	public void setReport(MachineLearningReport report) {
		this.report = report;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "Simulation [id=" + id + ", simulationDate=" + simulationDate + ", user=" + user + ", report=" + report
				+ ", application=" + application + "]";
	}
	
	
	
}
