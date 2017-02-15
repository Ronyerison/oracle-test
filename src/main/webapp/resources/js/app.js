var app = angular.module("oracle-test", [ 'ngResource', 'ui.router',
		'ngCookies' ]);

app.run(function($rootScope, $location, $state, LoginService) {
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams,
			fromState, fromParams) {
		console.log('Changed state to: ' + toState);
	});

	if (!LoginService.isAuthenticated()) {
		$state.transitionTo('login');
	}
});

app.config([ '$stateProvider', '$urlRouterProvider', '$locationProvider',
		function($stateProvider, $urlRouterProvider, $locationProvider) {

			$locationProvider.html5Mode(true);

			$urlRouterProvider.otherwise('login');

			$stateProvider.state('todo', {
				url : '/',
				templateUrl : 'views/index.jsp',
				controller : 'TodoController'
			}).state('list', {
				url : '/list',
				templateUrl : 'views/list.jsp',
				controller : 'TodoController'
			}).state('application', {
				url : '/application',
				views: {
					'': {
						templateUrl : 'views/application.html',
						controller: 'ApplicationController'
					},
					'header-view@application' : {
						templateUrl :'views/partials/menu.html',
						controller : 'UserController'
					}
				}
			}).state('login', {
				url : '/login',
				templateUrl : 'views/login.html',
				controller : 'UserController'
			});

		} ]);
