/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufpi.loes.oracleTest.web.model.Simulation;

/**
 * @author Rony
 *
 */
@RequestScoped
public class SimulationDao extends GenericDao<Simulation>{

	private ApplicationDao applicationDao;
	
	protected SimulationDao() {
		this(null, null);
	}
	
	@Inject
	public SimulationDao(EntityManager entityManager, ApplicationDao applicationDao) {
		super(entityManager);
		this.applicationDao = applicationDao;
	}
	
	public void insert(Simulation simulation, String applicationName) {
		simulation.setApplication(applicationDao.getApplicationByName(applicationName));
		super.insert(simulation);
	}
	
	public List<Simulation> findSimulationsByApplication(Long applicationId){
		try {
			TypedQuery<Simulation> query = entityManager.createQuery("Select s from Simulation s where lower(s.application.id) = :applicationId", Simulation.class);
			query.setParameter("applicationId", applicationId);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
