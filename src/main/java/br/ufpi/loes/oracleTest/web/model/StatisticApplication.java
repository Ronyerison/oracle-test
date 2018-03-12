package br.ufpi.loes.oracleTest.web.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class StatisticApplication implements Serializable {

	private static final long serialVersionUID = -8697678564990719072L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Calendar saveDate;

	private Long actionsNumber;

	@ManyToOne
	private Application application;

	public StatisticApplication() {
	}
	
	public StatisticApplication(Calendar saveDate, Long actionsNumber, Application application) {
		super();
		this.saveDate = saveDate;
		this.actionsNumber = actionsNumber;
		this.application = application;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Calendar saveDate) {
		this.saveDate = saveDate;
	}

	public Long getActionsNumber() {
		return actionsNumber;
	}

	public void setActionsNumber(Long actionsNumber) {
		this.actionsNumber = actionsNumber;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@Override
	public String toString() {
		return "StatisticApplication [id=" + id + ", saveDate=" + saveDate + ", actionsNumber=" + actionsNumber + "]";
	}

}
