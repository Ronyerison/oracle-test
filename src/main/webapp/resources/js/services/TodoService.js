angular.module("oracle-test").service("TodoService", [ '$resource', function($resource) {
	return $resource("backend/todo/:id", {
		id : '@_id'
	}, {
		update : {
			method : 'PUT'
		}
	});
} ]);