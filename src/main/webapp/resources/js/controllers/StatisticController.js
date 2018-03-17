angular.module("oracle-test").controller('StatisticController', [ '$scope', '$cookieStore', '$state', '$stateParams', '$filter', 'StatisticService', function($scope, $cookieStore, $state, $stateParams, $filter, StatisticService) {
	
	function stringToMatriz(stringM) {
		stringM = stringM.split("], ");
		
		// removendo todos caracteres '[', ']' e ' ', e tranformando em uma matriz de string
		for(s in stringM) {
			while(stringM[s].indexOf("[") != -1) {
				stringM[s] = stringM[s].replace("[", "");
			}
			
			if(s == stringM.length-1) {
				while(stringM[s].indexOf("]") != -1) {
					stringM[s] = stringM[s].replace("]", "");
				}
			}
			
			stringM[s] = stringM[s].split(",");
		}
		
		return stringM;
	}
	
	$scope.getTotalActions = function() {
		StatisticService.getStatisticApplication($stateParams.id).success(function(data) {
			var numberActions = 0;
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
			
			window.statistics = new Chart(ctx, config);
			
		}).error(function(data, status) {
			//TODO: ADD MENSAGEM DE ERRO
			console.log("Deu errado ao buscar as estastisticas da applicação !!!");
		});
	};
	
	// Converte a string que representa a matriz
	$scope.matrizConfusion = function() {
		var matriz = [["V"], ["F"], ["PF"]];
		var m = stringToMatriz($scope.simulation.report.confusionMatrixString);
		
		for(x in matriz) {
			matriz[x] = matriz[x].concat(m[x]);
		}
		
		$scope.simulation.report.confusionMatrixString = matriz;
	}
	
	$scope.getSimulation = function() {
		var simulation = StatisticService.getSimulation($stateParams.idSimulation);
		
		if(simulation !== undefined) {
			console.log("DADOS DA SIMULACAO: ", simulation);
			$scope.simulation = simulation;
		} else {
			console.log("ERRO: simulação não encontrada !!!");
		}
	}
	
	$scope.pieReportPct = function() {
		var config = {
			type: 'pie',
			data: {
				datasets: [{
					data: [
						$filter('number')($scope.simulation.report.pctCorrect),
						$filter('number')($scope.simulation.report.pctIncorrect)
					],
					backgroundColor: [
						window.chartColors.green,
						window.chartColors.red
					],
					label: 'Dataset 1'
				}],
				labels: [
					'Pct Correto (%): ',
					'Pct Incorreto (%): '
				]
			},
			options: {
				responsive: true,
				legend: {
					display: false,
					position: 'top',
				},
				title: {
					display: true,
					text: 'Percentual de Avaliacao'
				},
				animation: {
					animateScale: true,
					animateRotate: true
				}
			}
		}
		
		var ctx = document.getElementById('pieReportPct').getContext('2d');
		window.doughnutReport = new Chart(ctx, config);
	}
	
	$scope.doughnutReportIndices = function() {
		var config = {
			type: 'doughnut',
			data: {
				datasets: [{
					data: [
						$filter('number')($scope.simulation.report.kappa),
						$filter('number')($scope.simulation.report.errorrate)
					],
					backgroundColor: [
						window.chartColors.blue,
						window.chartColors.yellow
					],
					label: 'Dataset 1'
				}],
				labels: [
					'Kappa: ',
					'Error Rate: '
				]
			},
			options: {
				responsive: true,
				legend: {
					display: false,
					position: 'top',
				},
				title: {
					display: true,
					text: 'Indices (Kappa e Erro Rate)'
				},
				animation: {
					animateScale: true,
					animateRotate: true
				}
			}
		}
		
		var ctx = document.getElementById('doughnutReportIndices').getContext('2d');
		window.doughnutReport = new Chart(ctx, config);
	}
	
	$scope.simulations = function() {
		$scope.application = {
				id: $stateParams.id,
				name: $stateParams.name,
				url: $stateParams.url
		}
		
		StatisticService.getSimulations($scope.application).success(function(data) {
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
