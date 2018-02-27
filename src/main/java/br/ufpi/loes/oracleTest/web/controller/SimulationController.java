package br.ufpi.loes.oracleTest.web.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.ufpi.loes.oracleTest.common.controller.BaseController;
import br.ufpi.loes.oracleTest.core.dataPreparation.DataPreparation;
import br.ufpi.loes.oracleTest.core.machineLearning.MachineLearning;
import br.ufpi.loes.oracleTest.web.repository.ReportDao;

/**
 * 
 * @author jsrib
 *
 */
@Controller
@Path("/backend/simulation")
public class SimulationController extends BaseController  {
	private final Result result;
	private final ReportDao reportDao;
	private final MachineLearning machineLearning;
	private final DataPreparation dataPreparation;
	
	public SimulationController() {
		this(null, null, null, null);
	}
	
	public SimulationController(Result result, ReportDao reportDao, MachineLearning machineLearning, DataPreparation dataPreparation) {
		this.result = result;
		this.reportDao = reportDao;
		this.machineLearning = machineLearning;
		this.dataPreparation = dataPreparation;
	}
	
	@Get("/{applicationName}")
	public void executeMethod(String applicationName) {
		machineLearning.inicializeInstances(applicationName);
		machineLearning.inicializeAlgorithm();
		reportDao.insert(machineLearning.getReport(), applicationName);
		result.use(Results.json()).withoutRoot().from(machineLearning.getReport()).serialize();
	}
}
