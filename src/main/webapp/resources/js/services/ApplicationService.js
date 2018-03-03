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
	
	function listApplicationUser(user) {
		return $http.get("http://localhost:8080/oracle-test/backend/users/applications/"+user.id);
	}
	
	return {
		addApplication: addApplication,
		listApplicationUser: listApplicationUser
	}
} ]);