package br.ufpi.loes.oracleTest.web.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;
import br.ufpi.loes.oracleTest.common.controller.BaseController;
import br.ufpi.loes.oracleTest.web.model.MachineLearningReport;
import br.ufpi.loes.oracleTest.web.repository.ReportDao;

@Controller
@Path("/backend/report")
public class ReportController extends BaseController{
	
	private Result result;
	private ReportDao reportDao;

	protected ReportController() {
		this(null, null);
	}
	
	@Inject
	public ReportController(Result result, ReportDao reportDao) {
		this.result = result;
		this.reportDao = reportDao;
	}
	
	@Get("")
	public void get() {
		result.use(Results.json()).withoutRoot().from(reportDao.findAll())
				.serialize();
	}
	
	@Get("/{report.id}")
	public void getOne(MachineLearningReport report) {
		result.use(Results.json()).withoutRoot()
				.from(reportDao.find(report.getId())).serialize();

	}
	
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Post("")
	public void save(MachineLearningReport report) {
		try {
			reportDao.insert(report);
			addSucessMessage("Relatorio persistido com sucesso!");
			result.use(Results.json()).withoutRoot()
					.from(messages).serialize();
		} catch (Exception e) {
			addErrorMessage("Problema ao salvar relatorio!");
			result.use(Results.json()).withoutRoot()
					.from(messages).serialize();
		}

	}
	
	@Delete("/{report.id}")
	public void delete(MachineLearningReport report) {
		try {
			reportDao.delete(report.getId());
			addSucessMessage("Relatorio removido com sucesso!");
			result.use(Results.json()).withoutRoot()
					.from(messages).serialize();
		} catch (Exception e) {
			addErrorMessage("Problema ao remover relatorio!");
			result.use(Results.json()).withoutRoot()
					.from(messages).serialize();
		}

	}
}
