angular.module("vraptor").service("ApplicationService", [ '$resource', function($resource) {
	return $resource("application/:id", {
		id : '@_id'
	}, {
		update : {
			method : 'PUT'
		}
	});
} ]);