/**
 *
 */

angular.module("oracle-test").controller('ActionController', ['$scope', 'ActionService', function ($scope, ActionService){
	
	$scope.list = function() {
		$scope.applicationName;
		
		ActionService.listActions($scope.applicationName).success(function(data) {
			
			$scope.actions = data;
			
		}).error(function(data, status) {
			
			console.log("Deu errado");
			
		});
	};
	
	$scope.generateInvalideActions = function() {
		ActionService.generateInvalideActions($scope.applicationName).success(function(data) {
			$scope.list($scope.applicationName);
		}).error(function(data, status) {
			
			console.log("Deu errado");
			
		});
	}
	
	$scope.simulation = function() {
		ActionService.executeMethod($scope.applicationName).success(function() {
			console.log("Methodo execultado com sucesso !!!");
		}).error(function(data, status) {
			
			console.log("Erro ao execultar o metodo !!!");
			
		});
	}
	
}]);
