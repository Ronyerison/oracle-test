/**
 * 
 */
package br.ufpi.loes.oracleTest.web.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;
import br.ufpi.loes.oracleTest.common.controller.BaseController;
import br.ufpi.loes.oracleTest.core.dataPreparation.DataPreparation;

/**
 * @author Rony
 *
 */
@Controller
@Path("/backend/preparation")
public class DataPreparationController extends BaseController{
	private final Result result;
	private final DataPreparation preparation;
	
	public DataPreparationController() {
		this(null, null);
	}

	@Inject
	public DataPreparationController(Result result, DataPreparation preparation) {
		this.result = result;
		this.preparation = preparation;
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Post("")
	public void preparateData(String applicationName){
		try {
			preparation.preparateActions(applicationName);
			addSucessMessage("Ações preparadas com sucesso!");
			result.use(Results.json()).withoutRoot()
					.from(messages).serialize();
		} catch (Exception e) {
			addErrorMessage("Problema ao preparar dados!");
			result.use(Results.json()).withoutRoot()
					.from(messages).serialize();
			e.printStackTrace();
		}
	}
}
