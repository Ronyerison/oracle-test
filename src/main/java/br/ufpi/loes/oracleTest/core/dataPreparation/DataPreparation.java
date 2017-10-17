/**
 * 
 */
package br.ufpi.loes.oracleTest.core.dataPreparation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import br.ufpi.loes.oracleTest.web.model.Action;
import br.ufpi.loes.oracleTest.web.repository.ActionsDao;

/**
 * @author Rony
 *
 */
public class DataPreparation implements Serializable{

	private static final long serialVersionUID = 7008960070106137865L;
	
	@Inject
	private ActionsDao actionsDao;

	public DataPreparation() {
	}

	public void preparateActions(String applicationName) {
		List<Action> actions = actionsDao.findPassActionsByApplication(applicationName);
		List<String> oracleURLs = actionsDao.listOracleURL(applicationName);
		List<Action> dubiousActions = generateDubious(actions);
		List<Action> failActions = generateFails(actions, oracleURLs);
		
		actionsDao.saveActions(failActions);
		actionsDao.saveActions(dubiousActions);
		
	}

	private List<Action> generateDubious(List<Action> actions) {
		Random rand = new Random();
		List<Action> dubiousActions = new ArrayList<Action>();
		int numActions = (int) (actions.size() * 0.15);
		
		for (int i = 0; i < numActions; i++) {
			int pos = rand.nextInt(actions.size());
			dubiousActions.add(actions.get(pos).clone());
		}
		
		for (Action action : dubiousActions) {
			int newNum = rand.nextInt(60) - 30;
			if(newNum >= 0 && newNum < 10) {
				newNum += 10;
			}
			if(newNum < 0 && newNum > -10) {
				newNum -= 10;
			}
			action.setsOracleVisibleElements(action.getsOracleVisibleElements() + newNum);
			action.setId(null);
			action.setsOracleVeredict("DUBIOUS");
		}
		
		return dubiousActions;
	}

	private List<Action> generateFails(List<Action> actions, List<String> oracleURLs) {
		Random rand = new Random();
		List<Action> failActions = new ArrayList<Action>();
		int numActions = (int) (actions.size() * 0.20);
		
		for (int i = 0; i < numActions; i++) {
			int pos = rand.nextInt(actions.size());
			failActions.add(actions.get(pos).clone());
		}
		
		for (Action action : failActions) {
			Integer newNum = rand.nextInt(2000);
			action.setsOracleVisibleElements(newNum.longValue());
			
			int posOracleURL;
			do {
				posOracleURL = rand.nextInt(oracleURLs.size());
			}while(action.getsOracleUrl().equalsIgnoreCase(oracleURLs.get(posOracleURL)));
			
			if(!action.getsOracleUrl().equalsIgnoreCase(oracleURLs.get(posOracleURL))){
				action.setsOracleUrl(oracleURLs.get(posOracleURL));
			}
			action.setId(null);
			action.setsOracleVeredict("FAIL");
		}
		
		return failActions;
	}
}
