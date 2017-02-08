package br.ufpi.loes.oracleTest.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.caelum.vraptor.view.Results;
import br.ufpi.loes.oracleTest.model.Application;
import br.ufpi.loes.oracleTest.model.Todo;
import br.ufpi.loes.oracleTest.repository.ApplicationDao;

@Controller
@Path("/backend/application")
public class ApplicationController {

	private final Result result;
	private final ApplicationDao applicationDao;
	
	protected ApplicationController() {
		this(null, null);
	}

	@Inject
	public ApplicationController(Result result, ApplicationDao applicationDao) {
		this.result = result;
		this.applicationDao = applicationDao;
	}

	@Get("")
	public void get() {
		result.use(Results.json()).withoutRoot().from(applicationDao.findAll())
				.serialize();
	}
	
	@Get("/{application.id}")
	public void getOne(Todo todo) {
		result.use(Results.json()).withoutRoot()
				.from(applicationDao.find(todo.getId())).serialize();

	}

	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Post("")
	public void create(Application application) {
		try {
			result.use(Results.json()).withoutRoot()
					.from(applicationDao.create(application)).serialize();
		} catch (Exception e) {
			result.include(e);
		}

	}

	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Put("")
	public void update(Application application) {
		result.use(Results.json()).withoutRoot()
				.from(applicationDao.update(application)).serialize();

	}

	@Delete("/{application.id}")
	public void delete(Todo todo) {
		result.use(Results.json()).withoutRoot()
				.from(applicationDao.delete(todo.getId())).serialize();

	}

}
