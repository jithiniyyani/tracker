// app.js
var app = angular.module('app', ['ui.router','ngMap','ui.bootstrap','ngAnimate','ngFileUpload']);

//to split in different files latter
app.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/landing');
    $stateProvider
        .state('about', {
            url: '/about',
            templateUrl: 'fragments/about.html'
        })
        .state('alerts', {
            url: '/alerts',
            templateUrl: 'fragments/alerts.html'
        })
        .state('disclaimer', {
            url: '/disclaimer',
            templateUrl: 'fragments/disclaimer.html'
        })
        .state('profile', {
            url: '/profile',
            templateUrl: 'fragments/profile.html'
        })
        .state('termsAndConditions', {
            url: '/termsAndConditions',
            templateUrl: 'fragments/termsAndConditions.html'
        })
        .state('vehicle', {
            url: '/vehicle',
            templateUrl: 'fragments/vehicle.html'
        })
        .state('signup', {
            url: '/signup',
            templateUrl: 'fragments/signup.html'
        })
        .state('landing', {
            url: '/landing',
            templateUrl: 'fragments/landing.html'
        })
        .state('forgotPassword', {
            url: '/forgotPassword',
            templateUrl: 'fragments/forgotPassword.html'
        })

        .state('lost', {
            url: '/lost',
            templateUrl: 'fragments/lost.html'
        })

        .state('found', {
            url: '/found',
            templateUrl: 'fragments/found.html'
        })

        .state('status', {
            url: '/status',
            templateUrl: 'fragments/status.html'
        })
        .state('howitworks', {
            url: '/howitworks',
            templateUrl: 'fragments/howitworks.html'
        })
        .state('viewtheft', {
            url: '/viewtheft',
            templateUrl: 'fragments/viewtheft.html'
        })
        .state('login',{
          url : '/login',
          templateUrl: 'fragments/login.html'

        });
});
