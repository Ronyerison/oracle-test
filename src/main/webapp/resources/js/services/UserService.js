angular.module("oracle-test").factory("LoginService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore) {
	var userInfo = null;
	var isLogged = false;
	var result = null;
	
	function isAuthenticated() {
		return isLogged;
	}
	
	function login(email, password) {
		var deferred = $q.defer();
		var user = {
			email: email,
			password: password
		}
		$http.post("http://localhost:8080/oracle-test/backend/users/login", JSON.stringify(user))
			.then(function (result){
				if(result.status == 200){
					userInfo = {
						email: result.data.email,
						name: result.data.name,
						login: result.data.login
					}
					isLogged = true;
					var date = new Date();
					date.setTime(date.getTime() + (30 * 3600000));
					
					$cookieStore.put("userInfo", JSON.stringify(userInfo), {'expires': date});
					deferred.resolve(userInfo);
				}else{
					deferred.reject(result);
				}
			}, function(error) {
				deferred.reject(error);
			});
		
		return deferred.promise;
	};
	
	function getUserInfo(){
		return userInfo;
	}
	
	return{
		login: login,
		isAuthenticated: isAuthenticated,
		userInfo: getUserInfo
	};
}]);
		