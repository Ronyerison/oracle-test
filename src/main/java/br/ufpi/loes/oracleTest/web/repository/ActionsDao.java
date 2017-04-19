/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ufpi.loes.oracleTest.web.model.Action;

/**
 * @author Ronyerison
 *
 */
public class ActionsDao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	public void saveActions(List<Action> actions) {
		try {
			for (Action action : actions) {
				em.merge(new Action(action));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
