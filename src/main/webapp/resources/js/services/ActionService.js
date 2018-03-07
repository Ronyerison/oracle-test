angular.module("oracle-test").service("ActionService", ["$http", '$q', "$cookieStore", function ($http, $q, $cookieStore){
	
	function listActions(applicationName) {
		return $http.get("http://localhost:8080/oracle-test/backend/actions/"+applicationName);
	}
	
	function executeMethod(applicationName) {
		return $http.get("http://localhost:8080/oracle-test/backend/simulation/execute/"+applicationName);
	}
	
	function generateInvalideActions(applicationName) {
		return $http.get("http://localhost:8080/oracle-test/backend/simulation/preparate/"+applicationName);
	}
	
	return {
		listActions: listActions,
		executeMethod: executeMethod,
		generateInvalideActions: generateInvalideActions
	}
	
}]);
