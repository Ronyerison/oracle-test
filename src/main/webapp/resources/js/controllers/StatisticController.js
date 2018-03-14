angular.module("oracle-test").controller('StatisticController', [ '$scope', '$cookieStore', '$state', '$stateParams', '$filter', 'StatisticService', function($scope, $cookieStore, $state, $stateParams, $filter, StatisticService) {
	var totalActions = 0;
	
	$scope.getTotalActions = function() {
		$scope.totalActions = totalActions;
	}
	
	$scope.statisticAction = function() {
		StatisticService.getStatistics($stateParams.id).success(function(data) {
			var number = [], dates = [];
			for(var i = 0; i < data.length; i++) {
				number[i] = data[i].actionsNumber;
				dates[i] = $filter('date')((data[i].saveDate), "dd/MM/yyyy");
			}
			
			console.log(totalActions);
			var ctx = document.getElementById('canvas').getContext('2d');
			var config = {
				type: 'line',
				data: {
					labels: dates,
					datasets: [{
						label: 'Actions',
						backgroundColor: "#c05",
						borderColor: "#c05",
						data: number,
						fill: false
					}]
				},
				options: {
					responsive: true,
					title: {
						display: true,
						text: 'Acoes Capturadas'
					},
					tooltips: {
						mode: 'index',
						intersect: false,
					},
					hover: {
						mode: 'nearest',
						intersect: true
					},
					scales: {
						xAxes: [{
							display: true,
							scaleLabel: {
								display: true,
								labelString: 'Dia'
							}
						}],
						yAxes: [{
							display: true,
							scaleLabel: {
								display: true,
								labelString: 'Numero Acoes'
							}
						}]
					}
				}
			}
			
			window.myLine = new Chart(ctx, config);
			
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado ao buscar as estastisticas da applicação !!!");
		});	
	};
}]);