/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufpi.loes.oracleTest.web.exceptions.OracleException;
import br.ufpi.loes.oracleTest.web.model.Action;
import br.ufpi.loes.oracleTest.web.model.Application;

/**
 * @author Ronyerison
 *
 */
public class ActionsDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	@Inject
	private ApplicationDao applicationDao;
	
	public void saveActions(List<Action> actions) {
		try {
			Application application = applicationDao.getApplicationByCaptureCode(actions.get(0).getCaptureCode());
			if(application != null) {
				for (Action action : actions) {
					action.setApplication(application);
					em.persist(new Action(action));
				}
			}else {
				throw new OracleException("Código da Aplicação Inválido!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveInvalidActions(List<Action> actions) {
		try {
			for (Action action : actions) {
//				action.setApplication(applicationDao.getApplicationByCaptureCode(action.getCaptureCode()));
				em.persist(action);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Action> findActionsByApplication(String applicationName){
		try {
			TypedQuery<Action> query = em.createQuery("Select a from Action a where lower(a.sClient) like :applicationName", Action.class);
			query.setParameter("applicationName", '%' + applicationName.toLowerCase() + '%');
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Action> findPassActionsByApplication(String applicationName){
		try {
			TypedQuery<Action> query = em.createQuery("Select a from Action a where lower(a.sClient) like :applicationName AND a.sOracleVeredict like '%PASS%'", Action.class);
			query.setParameter("applicationName", '%' + applicationName.toLowerCase() + '%');
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> listOracleURL(String applicationName){
		try {
			TypedQuery<String> query = em.createQuery("Select distinct a.sOracleUrl from Action a where lower(a.sClient) like :applicationName", String.class);
			query.setParameter("applicationName", '%' + applicationName.toLowerCase() + '%');
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Action> findPassActionsByApplicationId(Long applicationId){
		try {
			TypedQuery<Action> query = em.createQuery("Select a from Action a where a.application.id = :applicationId AND a.sOracleVeredict like '%PASS%'", Action.class);
			query.setParameter("applicationId", applicationId);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> listOracleURLByApplicationId(Long applicationId){
		try {
			TypedQuery<String> query = em.createQuery("Select distinct a.sOracleUrl from Action a where a.application.id = :applicationId", String.class);
			query.setParameter("applicationId", applicationId);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
