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
		$scope.applicationName;
		
		ActionService.generateInvalideActions($scope.applicationName).success(function(data) {
			
			$scope.actions = data;
			
		}).error(function(data, status) {
			
			console.log("Deu errado");
			
		});
	}
	
}]);
