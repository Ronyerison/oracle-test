/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufpi.loes.oracleTest.common.util.PasswordGenerator;
import br.ufpi.loes.oracleTest.web.exceptions.OracleException;
import br.ufpi.loes.oracleTest.web.model.Application;
import br.ufpi.loes.oracleTest.web.model.User;

/**
 * @author Ronyerison
 *
 */

public class ApplicationDao implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	public ApplicationDao() {
	}

	public List<Application> findAll() {
		try {
			return em.createQuery("Select a from Application a", Application.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Application find(Long id) {
		try {
			return em.find(Application.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Application create(Application application) throws Exception{
//		application.setId(new Random().nextLong());
		
		try {
			TypedQuery<User> query = em.createQuery("select u from User u where u.email like :email", User.class);
			query.setParameter("email", '%'+application.getOwner().getEmail()+'%');
			User user = query.getSingleResult();
			application.setOwner(user);
			application.setCaptureCode(PasswordGenerator.generateApplicationCode());
			return em.merge(application);
		} catch (Exception e) {
			e.printStackTrace();
			throw new OracleException("Problema ao Salvar Aplicação");
		}
	}

	public Application update(Application application) {
		try {
			em.merge(application);
			return application;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Application delete(Long id) {
		try {
			Application application = em.find(Application.class, id);
			em.remove(application);
			return application;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Application getApplicationByName(String name) {
		try {
			TypedQuery<Application> query = em.createQuery("Select a from Application a where a.name like lower(:name)", Application.class);
			query.setParameter("name", "%" + name + "%");
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Application getApplicationByCaptureCode(String captureCode) {
		try {
			TypedQuery<Application> query = em.createQuery("Select a from Application a where a.captureCode like :captureCode", Application.class);
			query.setParameter("captureCode", "%" + captureCode + "%");
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
