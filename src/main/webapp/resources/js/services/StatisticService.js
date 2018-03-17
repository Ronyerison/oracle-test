angular.module("oracle-test").factory("StatisticService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore){
	var statistics = null;
	var application = null;
	
	/**
	 * @author: José Reis R Santiago
	 * @description: retorna as Statistics de uma aplicação.
	 */
	function getStatisticApplication(idApp) {
		if(statistics == null) {
			statistics = $http.get("http://localhost:8080/oracle-test/backend/application/statistic/" + idApp);
		}
		
		return statistics;
	}
	
	/**
	 * @author: José Reis R Santiago
	 * @description: 
	 */
	function getSimulationsApp(app) {
		if(application==null || application.i != app.id) {
			application = app;
		}
		
		return $http.get("http://localhost:8080/oracle-test/backend/simulation/list/" + application.id);
	}
	
	/**
	 * @author: José Reis R Santiago
	 * @description: busca e retorna uma simulação de uma aplicação pelo id
	 */
	function getSimulation(idSimulation) {
		if(application != null) {
			for(var i = 0; i < application.qntSimulation; i++) {
				if(application.simulations[i].id == idSimulation) {
					return application.simulations[i];
				}
			}
		} else {
			return null;
		}
	}
	
	return {
		getSimulation: getSimulation,
		getSimulations: getSimulationsApp,
		getStatisticApplication: getStatisticApplication
	}
}]);