angular.module("oracle-test").controller('ApplicationController', [ '$scope', '$stateParams', '$cookieStore', '$state', '$filter', 'ApplicationService', function($scope, $stateParams, $cookieStore, $state, $filter, ApplicationService) {

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
	}
	
	$scope.deleteApp = function(idApplication) {
		console.log("idApp: ", idApplication);
		ApplicationService.deleteApplication(idApplication).success(function(data) {
			$state.reload();
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado a exclusão da aplicacao!!");
		});
	}
	
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
	
}]);