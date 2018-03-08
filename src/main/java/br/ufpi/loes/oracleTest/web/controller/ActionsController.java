/**
 * 
 */
package br.ufpi.loes.oracleTest.web.controller;

import java.util.List;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithRoot;
import br.com.caelum.vraptor.view.Results;
import br.ufpi.loes.oracleTest.common.controller.BaseController;
import br.ufpi.loes.oracleTest.core.dataPreparation.DataPreparation;
import br.ufpi.loes.oracleTest.core.machineLearning.MachineLearning;
import br.ufpi.loes.oracleTest.web.model.Action;
import br.ufpi.loes.oracleTest.web.model.Simulation;
import br.ufpi.loes.oracleTest.web.repository.ActionsDao;
import br.ufpi.loes.oracleTest.web.repository.ReportDao;
import br.ufpi.loes.oracleTest.web.repository.SimulationDao;

/**
 * @author Ronyerison
 *
 */
@Controller
@Path("/backend/actions")
public class ActionsController extends BaseController{

	private final Result result;
	private final ActionsDao actionsDao;
	@SuppressWarnings("unused")
	private final ReportDao reportDao;
	private final SimulationDao simulationDao;

	private final MachineLearning machineLearning;
	private final DataPreparation dataPreparation;

	public ActionsController() {
		this(null, null, null, null, null, null);
	}

	@Inject
	public ActionsController(Result result, ActionsDao actionsDao, MachineLearning machineLearning,
			DataPreparation dataPreparation, ReportDao reportDao, SimulationDao simulationDao) {
		this.result = result;
		this.actionsDao = actionsDao;
		this.machineLearning = machineLearning;
		this.dataPreparation = dataPreparation;
		this.reportDao = reportDao;
		this.simulationDao = simulationDao;
	}

	@Consumes(value = "application/json", options = WithRoot.class)
	@Post("")
	public void saveActions(Object acoes) {
		try {
			System.out.println(acoes.toString());
			List<Action> actions = toList((String) acoes);
			actionsDao.saveActions(actions);
			addSucessMessage("Ações persistidas!");
			result.use(Results.json()).withoutRoot().from(messages).serialize();
		} catch (Exception e) {
			addErrorMessage("Problema ao salvar ações!");
			result.use(Results.json()).withoutRoot().from(messages).serialize();
			e.printStackTrace();
		}
	}

	@Get("/{applicationName}")
	public void getActionsByApplication(String applicationName) {
		result.use(Results.json()).withoutRoot().from(actionsDao.findActionsByApplication(applicationName)).serialize();

	}
	
	/**
	 * 
	 * @param applicationId
	 */
//	@Get("/preparateActions/{applicationName}")
	/*public void preparateActions(String applicationName) {
		System.out.println(applicationName);
		try {
			dataPreparation.preparateActions(applicationName);
			addSucessMessage("Ações preparadas para execulção da simulação!");
		} catch (Exception e) {
			addErrorMessage("Erro ao preparar as ações para execulção da simulação!");
			e.printStackTrace();
		}
	}*/

//	@Get("/simulation/{applicationName}")
	@Get("/preparateActions/{applicationId}")
	public void executeMethod(Long applicationId) {
		try {
			dataPreparation.preparateActions(applicationId);
			machineLearning.inicializeInstances(applicationId);
			machineLearning.inicializeAlgorithm();
			System.out.println(machineLearning.getReport().toString());
//			reportDao.insert(machineLearning.getReport(), applicationName);
			Simulation simulation = new Simulation(null, machineLearning.getReport(), null);
			simulationDao.insert(simulation, applicationId);
			result.use(Results.json()).withoutRoot().from(machineLearning.getReport()).serialize();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static List<Action> toList(String json) {
		if (null == json) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<Action>>() {
		}.getType());
	}
}
