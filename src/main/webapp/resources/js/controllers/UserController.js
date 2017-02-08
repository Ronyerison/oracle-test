app.controller('UserController', function($scope, $rootScope, $stateParams,
		$state, $cookieStore, LoginService) {

	if ($cookieStore.get("userInfo")) {
		$scope.userInfo = JSON.parse($cookieStore.get("userInfo"));
    }
	
	$scope.formSubmit = function() {
		if ($scope.email !== undefined && $scope.password !== undefined) {
			LoginService.login($scope.email, $scope.password).success(function(data) {
				$scope.error = '';
				$scope.email = '';
				$scope.password = '';
				$state.go('application', {}, {reload: true})
			}).error(function(data, status){
				$scope.error = "Incorrect username/password !";
			});
			
		} 
	};

});
