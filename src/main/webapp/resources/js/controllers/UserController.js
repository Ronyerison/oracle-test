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
		$state.go('main.home', {}, {reload: true})
	}
	
	$scope.add = function() {
		if($scope.user.password === $scope.re_password) {
			LoginService.addUser($scope.user).success(function(data){
				$scope.user.name = '';
				$scope.user.email = '';
				$scope.user.password = '';
				$scope.re_password = '';
				console.log("Deu certo!! Usuario Salvo");
				console.log(JSON.stringify(data));
			}).error(function(data, status) {
				//TODO: ADD MENSAGEM DE ERRO
				console.log("Deu errado! Usuario nao salvo");
			});
		} else {
			$scope.error_register = "Password diferented Re-password";
		}
	}

});
