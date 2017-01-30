
<div class="jumbotron">
	<h3>Registrar uma nova aplicação</h3>
	<form name="application-form"">
		<input class="form-control" ng-model="application.name" id="title-app"
			placeholder="Nome da Aplicação" autofocus>
			
		<input class="form-control" ng-model="application.url" id="url-app"
			placeholder="Url da aplicação" >
			
		<button class="btn btn-primary btn-block" ng-click="add(application)">Salvar</button>
	</form>
	{{application}}
</div>
