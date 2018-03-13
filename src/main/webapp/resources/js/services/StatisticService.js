angular.module("oracle-test").factory("StatisticService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore){
	
	function getStatistics(idApplication) {
		return $http.get("http://localhost:8080/oracle-test/backend/application/statistic/" + idApplication);	
	}
	
	return {
		getStatistics: getStatistics
	}
}]);