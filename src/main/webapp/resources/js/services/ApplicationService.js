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
	
	function getApplication(application) {
		return $http.get("http://localhost:8080/oracle-test/backend/application/"+application.id);
	}
	
	function listApplicationUser(user) {
		return $http.get("http://localhost:8080/oracle-test/backend/users/applications/"+user.id);
	}
	
	function getSimulations(application) {
		var deferred = $q.defer();
		
		$http.get("http://localhost:8080/oracle-test/backend/simulation/list/" + application.id).then(function(result) {
			if(result.status == 200) {
				var app = {
						id: application.id,
						url: application.url,
						name: application.name,
						simulations: result.data
				}
				
				$cookieStore.put("application", JSON.stringify(app));
				deferred.resolve(app);
			} else{
				deferred.reject(result);
			}
		}, function(error) {
			deferred.reject(error);
		});
		
		return deferred.promise;
	}
	
	return {
		addApplication: addApplication,
		getApplication: getApplication,
		getSimulations: getSimulations,
		listApplicationUser: listApplicationUser,
	}
} ]);