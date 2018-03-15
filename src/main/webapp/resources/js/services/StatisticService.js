angular.module("oracle-test").factory("StatisticService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore){
	var result = null;
	
	function getStatisticApplication(idApp) {
		if(result == null) {
			console.log("result: ", result);
			result = $http.get("http://localhost:8080/oracle-test/backend/application/statistic/" + idApp);
		}
		
		return result;
	}
	
	function getSimulations(idApp) {
		return $http.get("http://localhost:8080/oracle-test/backend/simulation/list/" + idApp);	
	}
	
	return {
		getSimulations: getSimulations,
		getStatisticApplication: getStatisticApplication
	}
}]);