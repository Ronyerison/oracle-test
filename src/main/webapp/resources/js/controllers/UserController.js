app.controller('UserController', function($scope, $rootScope, $stateParams,
		$state, $cookieStore, $q, LoginService) {

	if ($cookieStore.get("userInfo")) {
		$scope.userInfo = JSON.parse($cookieStore.get("userInfo"));
    }
	
	$scope.login = function() {
		var deferred = $q.defer();
		if ($scope.email !== undefined && $scope.password !== undefined) {
			LoginService.login($scope.email, $scope.password).then(function(response){
				$scope.error = '';
				$scope.email = '';
				$scope.password = '';
				$state.go('dashboard.home', {}, {reload: true})
			}).catch(function(err){
				$scope.error = "Incorrect username/password !";
			});
			

			
		} 
	};
	
	$scope.logout = function() {
		$scope.userInfo = null;
		$cookieStore.remove("userInfo");
	}

});
