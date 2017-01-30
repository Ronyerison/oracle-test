angular.module("vraptor").controller('ApplicationCtrl', [ '$scope', 'ApplicationService', function($scope, ApplicationService) {
	$scope.application = new ApplicationService();
	
	$scope.add = function(application) {
		ApplicationService.save(application,function(){
			$scope.application = new ApplicationService();		
		});
	};
	
	$scope.applications = ApplicationService.query(); 
	
} ]);