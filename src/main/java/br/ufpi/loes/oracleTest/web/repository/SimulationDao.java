/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
}
