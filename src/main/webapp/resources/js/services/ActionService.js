angular.module("oracle-test").service("ActionService", ["$http", '$q', "$cookieStore", function ($http, $q, $cookieStore){
	
	function listActions(applicationName) {
		return $http.get("http://localhost:8080/oracle-test/backend/actions/"+applicationName);
	};
	
	function generateInvalideActions(applicationName) {
		$http.get("http://localhost:8080/oracle-test/backend/actions/simulation/"+applicationName);
		
		return listActions(applicationName);
	}
	
	return {
		listActions: listActions,
		generateInvalideActions: generateInvalideActions
	}
	
}]);
