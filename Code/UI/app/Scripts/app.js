// app.js
var app = angular.module('app', ['ui.router','ngMap','ui.bootstrap','ngAnimate','ngFileUpload','ui.bootstrap.datetimepicker']);

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
        .state('found.search', {
            url: '/search',
            templateUrl: 'fragments/find_search.html'
        })
        .state('found.upload', {
            url: '/upload',
            templateUrl: 'fragments/find_upload.html'
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
        .state('activateAccount', {
            url: '/activateUser',
            templateUrl: 'fragments/activateUser.html'
        })
        .state('rewards', {
            url: '/rewards',
            templateUrl: 'fragments/rewards.html'
        })
        .state('login',{
          url : '/login',
          templateUrl: 'fragments/login.html'

        });
});


app.constant('uiDatetimePickerConfig', {
        dateFormat: 'yyyy-MM-dd HH:mm',
        defaultTime: '00:00:00',
        html5Types: {
            date: 'yyyy-MM-dd',
            'datetime-local': 'yyyy-MM-ddTHH:mm:ss.sss',
            'month': 'yyyy-MM'
        },
        initialPicker: 'date',
        reOpenDefault: false,
        enableDate: true,
        enableTime: true,
        buttonBar: {
            show: true,
            now: {
                show: true,
                text: 'Now'
            },
            today: {
                show: true,
                text: 'Today'
            },
            clear: {
                show: true,
                text: 'Clear'
            },
            date: {
                show: true,
                text: 'Date'
            },
            time: {
                show: true,
                text: 'Time'
            },
            close: {
                show: true,
                text: 'Close'
            }
        },
        closeOnDateSelection: true,
        closeOnTimeNow: true,
        appendToBody: false,
        altInputFormats: [],
        ngModelOptions: { },
        saveAs: false,
        readAs: false,
    });
