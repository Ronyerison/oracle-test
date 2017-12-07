var app = angular.module("oracle-test", [ 'ngResource', 'ui.router',
		'ngCookies' ]);

app.run(function($rootScope, $location, $state, LoginService) {
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams,
			fromState, fromParams) {
		console.log('Changed state to: ' + toState);
		
		if(toState.name === 'login'){
			console.log('Entrou no login');
			return;
		}
		console.log(LoginService.isAuthenticated());
		if (LoginService.isAuthenticated() === false) {
			console.log('Entrou na verificacao');
			$state.transitionTo('login');
			event.preventDefault();
		}
		console.log('Passou direto!');
	});

	
});

app.config([
		'$stateProvider',
		'$urlRouterProvider',
		'$locationProvider',
		'$httpProvider',
		function($stateProvider, $urlRouterProvider, $locationProvider,
				$httpProvider) {

			$locationProvider.html5Mode(false);

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
				views : {
					'' : {
						templateUrl : 'views/application.html',
						controller : 'ApplicationController'
					},
					'header-view@application' : {
						templateUrl : 'views/partials/menu.html',
						controller : 'UserController'
					}
				}
			}).state('login', {
				url : '/login',
				templateUrl : 'views/login.html',
				controller : 'UserController'
			}).state('action', {
				url : '/actions',
				templateUrl : 'views/listActions.html',
				controller : 'ActionController'
			});

			// Enable cross domain calls
			$httpProvider.defaults.useXDomain = true;

			// Remove the header used to identify ajax call that would prevent
			// CORS from working
			delete $httpProvider.defaults.headers.common['X-Requested-With'];

		} ]);
