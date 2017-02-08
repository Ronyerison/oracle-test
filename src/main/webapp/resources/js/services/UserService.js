angular.module("oracle-test").factory("LoginService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore) {
	var userVO = null;
	var isLogged = false;
	
	function isAuthenticated(isLogged) {
		return isLogged;
	}
	
	function login(email, password) {
		var deferred = $q.defer();
		var user = {
			email: email,
			password: password
		}
		return $http.post("http://localhost:8080/oracle-test/backend/users/login", JSON.stringify(user));
	};
	
	
	
	return{
		login: login,
		isAuthenticated: isAuthenticated
	};
}]);
		
//		if(result.data.code == 200){
//			userVO = {
//				email: result.data.result.email,
//				name: result.data.result.name,
//				login: result.data.result.login
//			}
//			isLogged = true;
//			var date = new Date();
//			date.setTime(date.getTime() + (30 * 3600000));
//			
//			$cookieStore.put("userInfo", JSON.stringify(userVO), {'expires': date});
//			deferred.resolve(userInfo);
//		}else{
//			deferred.reject(result);
//		}
//	}, function(error) {
//		deferred.reject(error);
//	});