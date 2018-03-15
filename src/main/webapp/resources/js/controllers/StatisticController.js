angular.module("oracle-test").controller('StatisticController', [ '$scope', '$cookieStore', '$state', '$stateParams', '$filter', 'StatisticService', function($scope, $cookieStore, $state, $stateParams, $filter, StatisticService) {
	
	// retorna a lista de simulações de uma aplicação
	function getSimulations(idApp) {
		
	}
	
	$scope.getTotalActions = function() {
		StatisticService.getStatisticApplication($stateParams.id).success(function(data) {
			var numberActions = 0;
			console.log("total actions: ", data);
			for(var i = 0; i < data.length; i++) {
				numberActions = numberActions + data[i].actionsNumber;
			}
			
			$scope.actionsNumber = numberActions;
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("ERROR: não foi possivel calcular o total de ações capturadas !!!");
		});
	}
	
	$scope.statisticAction = function() {
		StatisticService.getStatisticApplication($stateParams.id).success(function(data) {
			console.log(data);
			var number = [], dates = [];
			for(var i = 0; i < data.length; i++) {
				number[i] = data[i].actionsNumber;
				dates[i] = $filter('date')((data[i].saveDate), "dd/MM/yyyy");
			}
			
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
	
	$scope.simulations = function() {
		$scope.application = {
				id: $stateParams.id,
				name: $stateParams.name,
				url: $stateParams.url
		}
		
		StatisticService.getSimulations($scope.application.id).success(function(data) {
			if(data.length > 0) {
				$scope.application.simulations = data;
				$scope.application.qntSimulation = data.length;
			} else {
				$scope.nullSimulations = "Nenhuma simulacao encontrada !!!";
			}
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado ao listar as applicações");
		});
	}
}])
