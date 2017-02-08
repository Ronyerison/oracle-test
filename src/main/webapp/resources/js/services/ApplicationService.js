angular.module("oracle-test").service("ApplicationService", [ '$resource', function($resource) {
	return $resource("backend/application/:id", {
		id : '@_id'
	}, {
		update : {
			method : 'PUT'
		}
	});
} ]);