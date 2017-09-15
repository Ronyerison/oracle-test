/**
 * 
 */
package br.ufpi.loes.oracleTest.core.dataPreparation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import br.ufpi.loes.oracleTest.web.model.Action;
import br.ufpi.loes.oracleTest.web.repository.ActionsDao;

/**
 * @author Rony
 *
 */
public class DataPreparation {

	@Inject
	private ActionsDao actionsDao;

	public DataPreparation() {
	}

	public List<Action> preparateActions(String applicationName) {
		List<Action> actions = actionsDao.findActionsByApplication(applicationName);
		List<Action> dubiousActions = generateDubious(actions);
		List<Action> failActions = generateFails(actions);
		actions.addAll(failActions);
		actions.addAll(dubiousActions);
		
		Collections.shuffle(actions);
		
		return actions;
	}

	private List<Action> generateDubious(List<Action> actions) {
		List<Action> dubiousActions = new ArrayList<Action>();
		int numActions = (int) (actions.size() * 0.15);
		
		for (int i = 0; i < numActions; i++) {
			dubiousActions.add(actions.get(i).clone());
		}
		
		Random rand = new Random();
		for (Action action : dubiousActions) {
			int newNum = rand.nextInt(60) - 30;
			if(newNum >= 0 && newNum < 10) {
				newNum += 10;
			}
			if(newNum < 0 && newNum > -10) {
				newNum -= 10;
			}
			
			action.setsOracleVisibleElements(action.getsOracleVisibleElements() + newNum);
		}
		
		return dubiousActions;
	}

	private List<Action> generateFails(List<Action> actions) {

		return null;
	}
}
