var app = angular.module('findMyCar', ['ngRoute']);

/* All the routing logic goes here */
app.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/', {
		templateUrl : 'views/default.html'
	}).when('/home', {
		templateUrl : 'views/home.html'
	}).when('/pagea', {
		templateUrl : 'views/pagea.html'
	}).when('/pageb', {
		templateUrl : 'views/pageb.html'
	}).when('/settings', {
		templateUrl : 'views/settings.html'
	}).when('/default', {
		templateUrl : 'views/default.html'
	}).otherwise({
		redirectTo : '/'
	});

} ]);