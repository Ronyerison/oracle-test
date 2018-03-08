angular.module("oracle-test").controller('ApplicationController', [ '$scope', '$stateParams', '$cookieStore', '$state', '$filter', 'ApplicationService', function($scope, $stateParams, $cookieStore, $state, $filter, ApplicationService) {
	
	if ($cookieStore.get("application")) {
		if($state.is('dashboard.view-application')){
			$scope.application = JSON.parse($cookieStore.get("application"));
			console.log($scope.application);
		} else {
			$cookieStore.remove("application");
		}
    }
	
	$scope.add = function() {
		$scope.userInfo = JSON.parse($cookieStore.get("userInfo"));
		$scope.application.owner = $scope.userInfo;
		
		console.log($scope.application, "----controller");
		
		ApplicationService.addApplication($scope.application).success(function(data) {
			//TODO: ADD MENSAGEM DE SUCESSO
			$scope.application = undefined;
			console.log("Deu certo");
			console.log(JSON.stringify(data));
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado");
		});
	};
	
	$scope.list = function() {
		$scope.userInfo = JSON.parse($cookieStore.get("userInfo"));
		
		ApplicationService.listApplicationUser($scope.userInfo).success(function(data) {
			console.log(data.length);
			if(data.length > 0) {
				$scope.listApplications = data;
			} else {
				$scope.nullApp = " ainda nao foi registrada nenhuma aplicacao !!!";
			}
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado ao listar as applicações");
		});
	}
	
	$scope.view = function() {
		var app = {
				id: $stateParams.id,
				name: $stateParams.name,
				url: $stateParams.url
		}
		
		ApplicationService.getSimulations(app).success(function(data) {
			if(data.length > 0) {
				app.simulations = data;
				
				$scope.application = app;
				
			} else {
				$scope.error = "Não existem simulações cadastradas !!!"
			}
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado ao listar as simulações da applicação");
		});
	}
	
	$scope.listSimulations = function(application) {
		ApplicationService.getSimulations(application).then(function(response) {
			$state.go('dashboard.view-application', {idApplication: application.id}, {reload: true});
		}).catch(function(err){
			$scope.error = "Error ao buscar a lista de simulações !!!";
		});
	}
	
}]);