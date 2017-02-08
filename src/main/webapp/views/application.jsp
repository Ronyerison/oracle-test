<div id="wrapper">
	<span class="menu"></span>

	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Registrar uma nova aplica��o para teste</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Informa��es da aplica��o</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">
								<form name="application-form"">
									<input class="form-control" ng-model="application.name"
										id="title-app" placeholder="Nome da Aplica��o" autofocus>
									<input class="form-control" ng-model="application.url"
										id="url-app" placeholder="Url da aplica��o">
									<div class="checkbox">
										<label> <input type="checkbox" ng-model="application.hasTestUser">Necessita de Usu�rio de teste
										</label>
									</div>
									<input class="form-control" ng-model="application.loginUser" ng-show="application.hasTestUser"
										id="url-app" placeholder="Usu�rio de Teste">
									<input class="form-control" type="password" ng-model="application.passwordUser" ng-show="application.hasTestUser"
										id="url-app" placeholder="Senha de Teste">
									
									<button class="btn btn-primary btn-block"
										ng-click="add(application)">Salvar</button>
									<a ui-sref="todo" class="btn btn-primary">NEW TODO</a> <a
										ui-sref="list" class="btn btn-primary">TODOS</a>
								</form>
								{{application}}
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
</div>