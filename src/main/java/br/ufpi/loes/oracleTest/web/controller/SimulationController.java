package br.ufpi.loes.oracleTest.web.controller;

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
public class SimulationController extends BaseController  {
	private final Result result;
	@SuppressWarnings("unused")
	private final ReportDao reportDao;
	private final MachineLearning machineLearning;
	private final DataPreparation dataPreparation;
	private final SimulationDao simulationDao;
	
	public SimulationController() {
		this(null, null, null, null, null);
	}
	
	public SimulationController(Result result, ReportDao reportDao, MachineLearning machineLearning, DataPreparation dataPreparation, SimulationDao simulationDao) {
		this.result = result;
		this.reportDao = reportDao;
		this.machineLearning = machineLearning;
		this.dataPreparation = dataPreparation;
		this.simulationDao = simulationDao;
	}
	
	@Get("/execute/{applicationName}")
	public void executeMethod(String applicationName) {
		try {
			machineLearning.inicializeInstances(applicationName);
			machineLearning.inicializeAlgorithm();
//			reportDao.insert(machineLearning.getReport(), applicationName);
			Simulation simulation = new Simulation(null, machineLearning.getReport(), null);
			simulationDao.insert(simulation, applicationName);
			result.use(Results.json()).withoutRoot().from(machineLearning.getReport()).serialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Get("/preparate/{applicationName}")
	public void preparateActions(String applicationName) {
		try {
			dataPreparation.preparateActions(applicationName);
			addSucessMessage("Dados preparados com sucesso!");
			result.use(Results.json()).withoutRoot().from(messages).serialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
