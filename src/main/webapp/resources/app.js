(function() {
	'use strict';
	/**
	 * Created by KrishnaReddy on 10/6/2015.
	 */
	angular.module('myApp',	[ 'ngRoute', 'ngCookies', 'ngMessages', 'ui.router' ]).config(config).run(run);

	config.$inject = [ '$routeProvider', '$locationProvider', '$httpProvider','$urlRouterProvider', '$stateProvider' ];
	function config($routeProvider, $locationProvider, $httpProvider,$urlRouterProvider, $stateProvider) {
		$stateProvider
				.state('home',{
							url : '/home',
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/course/courses.html',
									controller : 'CourseCtrl'
								}
							}

						})
				.state(
						'login',
						{
							url : '/login',
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/login/login.html',
								}
							}

						})

				.state(
						'signup',
						{
							url : '/signup',
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/signup/signup.html',
								}
							}

						})
				.state(
						'forgotPwd',
						{
							url : '/forgotPwd',
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/forgot/forgot.html',
								}
							}

						})
				.state(
						'success',
						{
							url : '/success',
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/signup/success.html',
								}
							}

						})

				.state(
						'about',
						{
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/about/aboutus.html',
								}
							}

						})

				.state(
						'contact',
						{
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/contact/contactus.html',
								}
							}

						})

				// Courses
				.state(
						'dashboard',
						{
							url : '/dashboard',
							views : {
								'content' : {
									templateUrl : '/eLearning/resources/modules/dashboard/dashboard.html',
								},
								resolve : {
									logincheck : checkLoggedin
								}
							}

						})

				// Courses
				.state('courses',{
							abstract : true,
							url : '/courses',
							templateUrl : '/eLearning/resources/modules/course/courses.html',
							controller : 'CourseCtrl'

						})
						
				.state('courses.enroll',{
		            	url : '/enroll/:id',
						views: {
				            'content@': {
								templateUrl : '/eLearning/resources/modules/course/course-show.html',
								controller : 'EnrollCourseCtrl'
				            }
						}
						})
				.state(
						'newCourse',
						{
							url : '/new',
							templateUrl : '/eLearning/resources/modules/course/course-new.html',
							controller : 'NewCourseCtrl'
						})
				.state(
						'editCourse',
						{
							url : '/:id/edit',
							templateUrl : '/eLearning/resources/modules/course/course-edit.html',
							controller : 'EditCourseCtrl'
						});

		$urlRouterProvider.otherwise('/home');

		$httpProvider.defaults.useXDomain = true;
		delete $httpProvider.defaults.headers.common['X-Requested-With'];
		//    
		// $locationProvider.html5Mode({
		// enabled: true,
		// requireBase: false
		// });

	}

	run.$inject = [ '$rootScope', '$location', '$cookieStore', '$http' ];

	var checkLoggedin = function checkLogin($q, $timeout, $http, $location,
			$rootScope, UserService) {

		var deferred = $q.defer();

		UserService.Login(vm.username, vm.password).then(function(user) {
			$rootScope.errorMessage = null;
			if (user.role_id !== 0) {
				$rootScope.currentUser = user;
				$rootScope.currentUser.isAdmin = true;
				deferred.resolve();
			} else {
				$rootScope.errorMessage = 'You need to log in.';
				deferred.reject();
				$location.url('/login')
			}
		});

		return deferred.promise;
	};

	function run($rootScope, $location, $cookieStore, $http) {
		// keep user logged in after page refresh
		$rootScope.globals = $cookieStore.get('globals') || {};
		if ($rootScope.globals.currentUser) {
			$http.defaults.headers.common['Authorization'] = 'Basic '
					+ $rootScope.globals.currentUser.authdata; // jshint
																// ignore:line
		}

		
		  $rootScope.$on('$locationChangeStart', function (event, next,
		  current) { // redirect to login page if not logged in and trying to access a restricted page 
		  var restrictedPage = $.inArray($location.path(), ['/home','/login', '/signup','/about','/contact','/success','/courses']) === -1; 
		  var  loggedIn = $rootScope.globals.currentUser;
		  if($location.path()==""){
			  $location.path('/home');
		  }else if (restrictedPage && !loggedIn) {
			  $location.path('/login'); }
		  });
		 
	}
})();
