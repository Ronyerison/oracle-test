var app = angular.module("vraptor", [ 'ngResource', 'ui.router' ]);

app.config([ '$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise('/');

	$stateProvider.state('todo', {
		url : '/',
		templateUrl : 'views/index.jsp',
		controller: 'TodoCtrl'
	}).state('list', {
		url : '/list',
		templateUrl : 'views/list.jsp',
		controller: 'TodoCtrl'
	}).state('application', {
		url: '/application',
		templateUrl: 'views/application.jsp',
		controller: 'ApplicationCtrl'
	});

} ]);

//var app = angular.module("vraptor", [ 'ngResource', 'ngRoute' ]);
//
//app.config(function($routeProvider) {
//	$routeProvider.when("/", {
//		templateUrl: "views/index.jsp",
//		controller: "TodoCtrl"
//	});
//	
//	$routeProvider.when("/list", {
//		templateUrl: "views/list.jsp",
//		controller: "TodoCtrl"
//	})
//})