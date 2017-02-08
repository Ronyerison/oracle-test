/**
 * 
 */
package br.ufpi.loes.oracleTest.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ufpi.loes.oracleTest.exceptions.OracleException;
import br.ufpi.loes.oracleTest.model.Application;

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
		application.setId(new Random().nextLong());
		try {
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
}
