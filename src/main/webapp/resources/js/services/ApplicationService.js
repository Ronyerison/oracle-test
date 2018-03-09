//angular.module("oracle-test").service("ApplicationService", [ '$resource', function($resource) {
//	return $resource("backend/application/:id", {
//		id : '@_id'
//	}, {
//		update : {
//			method : 'PUT'
//		}
//	});
//} ]);

angular.module("oracle-test").factory("ApplicationService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore){
	
	function addApplication(application){
		return $http.post("http://localhost:8080/oracle-test/backend/application", JSON.stringify(application));
	}
	
	function deleteApplication(idApplication) {
		return $http.delete("http://localhost:8080/oracle-test/backend/application/"+idApplication);
	}
	
	function getApplication(application) {
		return $http.get("http://localhost:8080/oracle-test/backend/application/"+application.id);
	}
	
	function listApplicationUser(user) {
		return $http.get("http://localhost:8080/oracle-test/backend/users/applications/"+user.id);
	}
	
	function getSimulations(application) {
		return $http.get("http://localhost:8080/oracle-test/backend/simulation/list/" + application.id);	
	}
	
	return {
		addApplication: addApplication,
		getApplication: getApplication,
		getSimulations: getSimulations,
		deleteApplication: deleteApplication,
		listApplicationUser: listApplicationUser,
	}
} ]);