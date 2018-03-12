/**
 * 
 */
package br.ufpi.loes.oracleTest.web.controller;

import java.util.Calendar;
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
import br.ufpi.loes.oracleTest.web.model.Application;
import br.ufpi.loes.oracleTest.web.model.StatisticApplication;
import br.ufpi.loes.oracleTest.web.repository.ActionsDao;
import br.ufpi.loes.oracleTest.web.repository.ApplicationDao;
import br.ufpi.loes.oracleTest.web.repository.ReportDao;
import br.ufpi.loes.oracleTest.web.repository.SimulationDao;
import br.ufpi.loes.oracleTest.web.repository.StatisticApplicationDao;

/**
 * @author Ronyerison
 *
 */
@Controller
@Path("/backend/actions")
public class ActionsController extends BaseController {

	private final Result result;
	private final ActionsDao actionsDao;
	@SuppressWarnings("unused")
	private final ReportDao reportDao;
	private final SimulationDao simulationDao;

	private final MachineLearning machineLearning;
	private final DataPreparation dataPreparation;
	private final StatisticApplicationDao statisticApplicationDao;
	private final ApplicationDao applicationDao;

	public ActionsController() {
		this(null, null, null, null, null, null, null, null);
	}

	@Inject
	public ActionsController(Result result, ActionsDao actionsDao, MachineLearning machineLearning,
			DataPreparation dataPreparation, ReportDao reportDao, SimulationDao simulationDao,
			StatisticApplicationDao statisticApplicationDao, ApplicationDao applicationDao) {
		this.result = result;
		this.actionsDao = actionsDao;
		this.machineLearning = machineLearning;
		this.dataPreparation = dataPreparation;
		this.reportDao = reportDao;
		this.simulationDao = simulationDao;
		this.statisticApplicationDao = statisticApplicationDao;
		this.applicationDao = applicationDao;
	}

	@Consumes(value = "application/json", options = WithRoot.class)
	@Post("")
	public void saveActions(Object acoes) {
		try {
			System.out.println(acoes.toString());
			List<Action> actions = toList((String) acoes);
			Application application = applicationDao.getApplicationByCaptureCode(actions.get(0).getCaptureCode());
			if(application != null) {
				actionsDao.saveActions(actions);
				statisticApplicationDao.saveStatistic(new StatisticApplication(Calendar.getInstance(), Long.valueOf(actions.size()), application));
				addSucessMessage("Ações persistidas!");
			}else {
				addErrorMessage("Código da aplicação inválido!");
			}
		} catch (Exception e) {
			addErrorMessage("Problema ao salvar ações!");
			e.printStackTrace();
		}
		result.use(Results.json()).withoutRoot().from(messages).serialize();
	}

	@Get("/{applicationName}")
	public void getActionsByApplication(String applicationName) {
		result.use(Results.json()).withoutRoot().from(actionsDao.findActionsByApplication(applicationName))
				.include("application").serialize();

	}

	/**
	 * 
	 * @param applicationId
	 */
	// @Get("/preparateActions/{applicationName}")
	/*
	 * public void preparateActions(String applicationName) {
	 * System.out.println(applicationName); try {
	 * dataPreparation.preparateActions(applicationName);
	 * addSucessMessage("Ações preparadas para execulção da simulação!"); } catch
	 * (Exception e) {
	 * addErrorMessage("Erro ao preparar as ações para execulção da simulação!");
	 * e.printStackTrace(); } }
	 */

	private static List<Action> toList(String json) {
		if (null == json) {
			return null;
		}
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<Action>>() {
		}.getType());
	}
}
