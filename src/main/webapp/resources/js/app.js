var isPrivaty = false;
var app = angular.module("oracle-test", [ 'ngResource', 'ui.router',
		'ngCookies' ]);

app.run(function($rootScope, $location, $state, LoginService) {
	$rootScope.$on('$stateChangeStart', function(event, toState, toParams,
			fromState, fromParams) {
		console.log('Changed state to: ' + toState.name);
		if(toState.name === 'main.login'){
			console.log('Entrou no login');
			return;
		}
		
		if(toState.name === 'dashboard') {
			isPrivaty = true;
		} else {
			isPrivaty = false;
		}
		
		console.log(LoginService.isAuthenticated());
		if (LoginService.isAuthenticated() === false && isPrivaty) {
			console.log('Entrou na verificacao');
			$state.transitionTo('main.login');
			event.preventDefault();
		}
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

			$urlRouterProvider.otherwise('/main/home');

			$stateProvider.state('main', {
				url : '/main',
				views : {
					'' : {
						templateUrl : 'views/index.html'
					},
					'header-view@main' : {
						controller : '',
						templateUrl : 'views/partials/header.html'
					},
					'footer-view@main' : {
						controller : '',
						templateUrl : 'views/partials/footer.html'
					}
				}
			}).state('main.home', {
				url : '/home',
				views : {
					'' : {
						controller : 'UserController',
						templateUrl : 'views/partials/home.html'
					}
				}
			}).state('main.login', {
				url : '/login',
				views : {
					'' : {
						controller : 'UserController',
						templateUrl : 'views/partials/login.html'
					}
				}
			}).state('dashboard', {
				url : '/dashboard',
				views : {
					'' : {
						templateUrl : 'views/dashboard/main.html'
					},
					'header-view@dashboard' : {
						templateUrl : 'views/partials/menu.html',
						controller : 'UserController'
					}
				}
			}).state('dashboard.home', {
				url : '/home',
				views : {
					'' : {
						controller : 'ApplicationController',
						templateUrl : 'views/dashboard/home.html'
					}
				}
			}).state('dashboard.register-application', {
				url : '/application/register',
				views : {
					'' : {
						controller : 'ApplicationController',
						templateUrl : 'views/dashboard/application/register.html'
					}
				}
			}).state('dashboard.list-application', {
				url : '/applications/list-applications',
				views : {
					'' : {
						controller : 'ApplicationController',
						templateUrl : 'views/dashboard/application/listApp.html'
					}
				}
			}).state('dashboard.view-application', {
				url : '/applications/view-application/?{id&name&url}',
				views : {
					'' : {
						controller : 'StatisticController',
						templateUrl : 'views/dashboard/application/application.html'
					}
				}
			}).state('dashboard.view-simulation', {
				url : '/applications/view-application/simulation/?{idSimulation&nameApp}',
				views : {
					'' : {
						controller : 'StatisticController',
						templateUrl : 'views/dashboard/application/simulation.html'
					}
				}
			}).state('dashboard.actions-application', {
				url : '/applications/application/actions',
				views : {
					'' : {
						controller : 'ActionController',
						templateUrl : 'views/dashboard/application/actions.html'
					}
				}
			});

			// Enable cross domain calls
			$httpProvider.defaults.useXDomain = true;

			// Remove the header used to identify ajax call that would prevent
			// CORS from working
			delete $httpProvider.defaults.headers.common['X-Requested-With'];

		} ]);
