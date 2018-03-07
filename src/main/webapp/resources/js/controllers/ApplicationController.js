angular.module("oracle-test").controller('ApplicationController', [ '$scope', '$cookieStore', '$state', 'ApplicationService', '$stateParams' , function($scope, $cookieStore, $state, ApplicationService) {
	
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
	
	$scope.listSimulations = function(application) {
		ApplicationService.getSimulations(application).then(function(response) {
			$state.go('dashboard.view-application', {idApplication: application.id}, {reload: true});
		}).catch(function(err){
			$scope.error = "Error ao buscar a lista de simulações !!!";
		})
			
	}
	
}]);