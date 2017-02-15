angular.module("oracle-test").controller('ApplicationController', [ '$scope', '$cookieStore', '$state', 'ApplicationService' , function($scope, $cookieStore, $state, ApplicationService) {
	
	$scope.add = function() {
		$scope.userInfo = JSON.parse($cookieStore.get("userInfo"));
		$scope.application.owner = $scope.userInfo;
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
	
} ]);