angular.module("oracle-test").controller('StatisticController', [ '$scope', '$cookieStore', '$state', '$stateParams', '$filter', 'StatisticService', function($scope, $cookieStore, $state, $stateParams, $filter, StatisticService) {
	
	$scope.statistic = function() {
		console.log($stateParams.id)
		StatisticService.getStatistics($stateParams.id).success(function(data) {
			if(data.length != undefined) {
//				$scope.statistic = data;
				console.log(data);
				
				Morris.Area({
					element: 'statistcs-actions',
					data: data,
					xkey: 'saveDate',
					ykeys: 'actionsNumber',
					pointSize: 2,
					hideHover: 'auto',
			        resize: true
				});
			} else {
				$scope.error = "Não foi encontrada nenhuma estatisca para a aplicacao !!!"
			}
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado ao buscar as estastisticas da applicação !!!");
		});
	}
	
}]);