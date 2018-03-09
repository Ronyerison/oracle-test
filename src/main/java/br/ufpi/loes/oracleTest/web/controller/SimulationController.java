package br.ufpi.loes.oracleTest.web.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ufpi.loes.oracleTest.common.controller.BaseController;
import br.ufpi.loes.oracleTest.core.dataPreparation.DataPreparation;
import br.ufpi.loes.oracleTest.core.machineLearning.MachineLearning;
import br.ufpi.loes.oracleTest.web.model.Simulation;
import br.ufpi.loes.oracleTest.web.repository.ReportDao;
import br.ufpi.loes.oracleTest.web.repository.SimulationDao;

/**
 * 
 * @author jsrib
 *
 */
@Controller
@Path("/backend/simulation")
public class SimulationController extends BaseController {
	private final Result result;
	@SuppressWarnings("unused")
	private final ReportDao reportDao;
	private final MachineLearning machineLearning;
	private final DataPreparation dataPreparation;
	private final SimulationDao simulationDao;

	public SimulationController() {
		this(null, null, null, null, null);
	}

	@Inject
	public SimulationController(Result result, ReportDao reportDao, MachineLearning machineLearning,
			DataPreparation dataPreparation, SimulationDao simulationDao) {
		this.result = result;
		this.reportDao = reportDao;
		this.machineLearning = machineLearning;
		this.dataPreparation = dataPreparation;
		this.simulationDao = simulationDao;
	}

	@Get("/execute/{applicationId}/{userId}")
	public void executeMethod(Long applicationId, Long userId) {
		try {
			machineLearning.inicializeInstances(applicationId);
			machineLearning.inicializeAlgorithm();
			// reportDao.insert(machineLearning.getReport(), applicationName);
			Simulation simulation = new Simulation(null, machineLearning.getReport(), null);
			simulationDao.insert(simulation, applicationId, userId);
			result.use(Results.json()).withoutRoot().from(machineLearning.getReport()).serialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Get("/preparate/{applicationId}")
	public void preparateActions(Long applicationId) {
		try {
			dataPreparation.preparateActions(applicationId);
			addSucessMessage("Dados preparados com sucesso!");
			result.use(Results.json()).withoutRoot().from(messages).serialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Get("/list/{applicationId}")
	public void listSimulationsByApplication(Long applicationId) {
		try {
			result.use(Results.json()).withoutRoot().from(simulationDao.findSimulationsByApplication(applicationId))
					.include("report").include("report.classMeasurements").include("user").serialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
