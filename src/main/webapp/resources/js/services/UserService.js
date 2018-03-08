angular.module("oracle-test").factory("LoginService", ["$http", "$q", "$cookieStore", function($http, $q, $cookieStore) {
	var userInfo = null;
	var isLogged = false;
	var result = null;
	
	function isAuthenticated() {
		return isLogged;
	}
	
	function addUser(user) {
		return $http.post("http://localhost:8080/oracle-test/backend/users", JSON.stringify(user));
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
						id: result.data.id,
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
	}
	
	function getUserInfo(){
		return userInfo;
	}
	
	return{
		login: login,
		addUser: addUser,
		isAuthenticated: isAuthenticated,
		userInfo: getUserInfo
	};
}]);
		