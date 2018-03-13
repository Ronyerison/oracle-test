angular.module("oracle-test").factory("StatisticService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore){
	
	function getSimulations(application) {
		return $http.get("http://localhost:8080/oracle-test/backend/simulation/list/" + application.id);	
	}
	
	return {
		getSimulations: getSimulations
	}
}]);