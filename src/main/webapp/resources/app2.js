(function(){
'use strict';
/**
 * Created by KrishnaReddy on 10/6/2015.
 */
angular
    .module('myApp', ['ngRoute', 'ngCookies','ngMessages'])
    .config(config)
    .run(run);

config.$inject = ['$routeProvider', '$locationProvider','$httpProvider'];
function config($routeProvider,$locationProvider,$httpProvider){

    $routeProvider
        .when('/',      { controller: 'HomeCtrl', templateUrl: 'modules/home/home.html',controllerAs: 'vm'})
        .when('/login', { controller: 'LoginCtrl', templateUrl: 'modules/login/login.html',controllerAs: 'vm' })
        .when('/signup', { controller: 'SignupCtrl', templateUrl: 'modules/signup/signup.html',controllerAs: 'vm' })
        .when('/success', { controller: 'SignupCtrl', templateUrl: 'modules/signup/success.html' })
        .when('/about', { templateUrl: 'modules/about/aboutus.html' })
        .when('/contact', { templateUrl: 'modules/contact/contactus.html' })
        .when('/forgotPwd', { templateUrl: 'modules/signup/forgot.html' })
        .otherwise({redirectTo: '/login'});

    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
}

run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
function run($rootScope, $location, $cookieStore, $http) {
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login', '/signup','/about','/contact','/success']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
}
})();