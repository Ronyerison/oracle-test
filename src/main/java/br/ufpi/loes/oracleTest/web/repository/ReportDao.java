package br.ufpi.loes.oracleTest.web.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ufpi.loes.oracleTest.web.model.MachineLearningReport;

@RequestScoped
public class ReportDao extends GenericDao<MachineLearningReport>{
	
	private ApplicationDao applicationDao;
	
	protected ReportDao() {
		this(null, null);
	}
	
	@Inject
	public ReportDao(EntityManager entityManager, ApplicationDao applicationDao) {
		super(entityManager);
		this.applicationDao = applicationDao;
	}
	
	public void insert(MachineLearningReport t, String applicationName) {
		t.setApplication(applicationDao.getApplicationByName(applicationName));
		super.insert(t);
	}
}
