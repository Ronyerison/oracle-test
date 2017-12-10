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
import br.ufpi.loes.oracleTest.web.repository.ActionsDao;
import br.ufpi.loes.oracleTest.web.repository.ReportDao;

/**
 * @author Ronyerison
 *
 */
@Controller
@Path("/backend/actions")
public class ActionsController extends BaseController{

	private final Result result;
	private final ActionsDao actionsDao;
	private final ReportDao reportDao;

	private final MachineLearning machineLearning;
	private final DataPreparation dataPreparation;

	public ActionsController() {
		this(null, null, null, null, null);
	}

	@Inject
	public ActionsController(Result result, ActionsDao actionsDao, MachineLearning machineLearning,
			DataPreparation dataPreparation, ReportDao reportDao) {
		this.result = result;
		this.actionsDao = actionsDao;
		this.machineLearning = machineLearning;
		this.dataPreparation = dataPreparation;
		this.reportDao = reportDao;
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

	@Get("/simulation/{applicationName}")
	public void executeMethod(String applicationName) {
		try {
			dataPreparation.preparateActions(applicationName);
			machineLearning.inicializeInstances(applicationName);
			machineLearning.inicializeAlgorithm();
			System.out.println(machineLearning.getReport().toString());
			reportDao.insert(machineLearning.getReport(), applicationName);
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
