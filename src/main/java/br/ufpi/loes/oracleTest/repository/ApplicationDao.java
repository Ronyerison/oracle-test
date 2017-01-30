/**
 * 
 */
package br.ufpi.loes.oracleTest.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ufpi.loes.oracleTest.model.Application;

/**
 * @author Ronyerison
 *
 */

public class ApplicationDao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Map<Long, Application> applicationList;

	@Inject
	private EntityManager em;
	
	public ApplicationDao() {
	}
	
	@PostConstruct
	public void init() {
		applicationList = new HashMap<>();
	}

	public List<Application> findAll() {
		return new ArrayList<>(applicationList.values());
	}

	public Application find(Long id) {
		return applicationList.get(id);
	}

	public Application create(Application application) {
		application.setId(new Random().nextLong());
		try {
			em.merge(application);
			return application;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Application update(Application application) {
		applicationList.put(application.getId(), application);
		return application;
	}

	public Application delete(Long id) {
		return applicationList.remove(id);
	}
}
