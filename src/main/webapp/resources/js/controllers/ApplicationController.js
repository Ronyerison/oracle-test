angular.module("oracle-test").controller('ApplicationController', [ '$scope', 'ApplicationService', function($scope, ApplicationService) {
	$scope.application = new ApplicationService();
	
	$scope.add = function(application) {
		ApplicationService.save(application,function(){
			$scope.application = new ApplicationService();		
		});
	};
	
	$scope.applications = ApplicationService.query(); 
	
} ]);