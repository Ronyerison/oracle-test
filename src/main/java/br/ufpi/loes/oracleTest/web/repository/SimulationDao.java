/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufpi.loes.oracleTest.web.interceptor.UserInfo;
import br.ufpi.loes.oracleTest.web.model.Application;
import br.ufpi.loes.oracleTest.web.model.Simulation;
import br.ufpi.loes.oracleTest.web.model.User;

/**
 * @author Rony
 *
 */
@RequestScoped
public class SimulationDao extends GenericDao<Simulation>{
	
	private UserDao userDao;
	private ApplicationDao applicationDao;
	
	protected SimulationDao() {
		this(null, null, null);
	}
	
	@Inject
	public SimulationDao(EntityManager entityManager, ApplicationDao applicationDao, UserDao userDao) {
		super(entityManager);
		this.applicationDao = applicationDao;
		this.userDao = userDao;
	}
	
	public void insert(Simulation simulation, Long applicationId, Long userId) {
		Application application = applicationDao.find(applicationId);
		User user = userDao.find(userId);
		simulation.setApplication(application);
		simulation.setUser(user);
		simulation.getReport().setApplication(application);
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
