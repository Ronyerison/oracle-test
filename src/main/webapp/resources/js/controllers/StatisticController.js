angular.module("oracle-test").controller('StatisticController', [ '$scope', '$cookieStore', '$state', '$stateParams', '$filter', 'StatisticService', function($scope, $cookieStore, $state, $stateParams, $filter, StatisticService) {
	
	$scope.statistic = function() {
		$scope.application = {
			id: $stateParams.id,
			name: $stateParams.name,
			url: $stateParams.url
		}
		
		StatisticService.getSimulations(app).success(function(data) {
			if(data.length > 0) {
				$scope.application.simulations = data;
				$scope.application.numberSimulations = data.length;
			} else {
				$scope.error = "Não existem simulações cadastradas !!!"
			}
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado ao listar as simulações da applicação");
		});
	}
	
}]);