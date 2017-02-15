app.controller('UserController', function($scope, $rootScope, $stateParams,
		$state, $cookieStore, $q, LoginService) {

	if ($cookieStore.get("userInfo")) {
		$scope.userInfo = JSON.parse($cookieStore.get("userInfo"));
    }
	
	$scope.login = function() {
		var deferred = $q.defer();
		if ($scope.email !== undefined && $scope.password !== undefined) {
			LoginService.login($scope.email, $scope.password).success(function(data) {

				//INSERINDO USUARIO LOGADO NOS COOKIES
				var date = new Date();
                date.setTime(date.getTime() + (10 * 3600000));
				$cookieStore.put("userInfo", JSON.stringify(data), {'expires': date})
				deferred.resolve(data);
				
				$scope.error = '';
				$scope.email = '';
				$scope.password = '';
				$state.go('application', {}, {reload: true})
			}).error(function(data, status){
				$scope.error = "Incorrect username/password !";
			});
			
		} 
	};
	
//	$scope.logout = function() {
//		$scope.userInfo = null;
//		$cookieStore.remove("userInfo");
//	}

});
