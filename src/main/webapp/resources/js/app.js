'use strict';

var app = angular.module("oracle-test", [ 'ngResource', 'ui.router', 'ngCookies']);

app.run(function($rootScope, $location, $state, LoginService) {
	    $rootScope.$on('$stateChangeStart', 
		      function(event, toState, toParams, fromState, fromParams){ 
		          console.log('Changed state to: ' + toState);
		      });
		    
		      if(!LoginService.isAuthenticated()) {
		        $state.transitionTo('login');
		      }
  	});

app.directive('menu', function() {
	return {
		restrict: 'C',
		templateUrl: 'resources/directives/menu.html'
	}
});

app.config([ '$stateProvider', '$urlRouterProvider', '$locationProvider',
		function($stateProvider, $urlRouterProvider, $locationProvider) {

			$urlRouterProvider.otherwise('/');

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
				templateUrl : 'views/application.jsp',
				controller : 'ApplicationController'
			}).state('login', {
				url : '/login',
				templateUrl : 'views/login.jsp',
				controller : 'UserController'
			});

			$locationProvider.html5Mode(true);

		} ]);


