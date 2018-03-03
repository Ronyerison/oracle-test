angular.module("oracle-test").controller('ApplicationController', [ '$scope', '$cookieStore', '$state', 'ApplicationService' , function($scope, $cookieStore, $state, ApplicationService) {
	
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
			console.log("Deu certo");
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
	
} ]);