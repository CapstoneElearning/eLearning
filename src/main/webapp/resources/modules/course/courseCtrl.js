/**
 * Created by KrishnaReddy on 10/16/2015.
 */
'use strict';
angular
		.module('myApp')
		.controller('CourseCtrl',['$scope','$state','$window','CourseService',
						function($scope, $state, $window, CourseService) {

							var vm = this;

							vm.course = null;
							vm.courses = [];
							vm.enrolledCourses = [];
							loadAllCourses();
							
							function loadEnrolledCourses() {
								CourseService
										.getCourses(
												$rootScope.globals.currentUser.username)
										.then(function(user) {
											vm.enrolledCourses = user.data;
										});
							}
							
							
							function loadAllCourses() {
								CourseService.GetAll().then(function(users) {
									vm.courses = users.data;
								});
							}
						} ])

		// New
		.controller('NewCourseCtrl',['$scope','$state',	'$filter','$stateParams','CourseService',
						function($scope, $state, $filter, $stateParams,	CourseService) {

							$scope.Course = {};
							$scope.newCourse = new Course();

							$scope.addCourse = function() {
								$scope.newCourse.author = $scope.Course.author;
								$scope.newCourse.title = $scope.Course.title;
								$scope.newCourse.image = $scope.Course.image;
								// convert date to string
								$scope.newCourse.release_date = $filter('date')
										($scope.Course.release_date_as_date,
												'longDate');
								$scope.newCourse.$save(function() {
									$state.go('courses');
								});
							};
						} ])

		// Show
		.controller('EnrollCourseCtrl',['$rootScope', '$scope','$route','$routeParams','$stateParams','CourseService',
						function($rootScope, $scope, $route, $routeParams,$stateParams, CourseService) {
						var vm = this;
						vm.course = null;
						
			            console.log("stateParams:id : "+$stateParams.id);
			            var  user = $rootScope.globals.UserProfile;
			            
			            vm.enrollCourse=enrollCourse;
			            
							function enrollCourse(){								
								CourseService.GetById($stateParams.id,user).then(function(users) {
									vm.course = users.data;
								});
							};						

						} ])

		// / Edit
		.controller(
				'EditCourseCtrl',
				[
						'$scope',
						'$state',
						'$filter',
						'$stateParams',
						'CourseService',
						function($scope, $state, $filter, $stateParams,
								CourseService) {

							$scope.Course = {};

							Course.get({
								id : parseInt($stateParams.id, 10)
							}, function(data) {

								var Course = data;

								var year = parseInt(data.release_date, 10);
								if (Number(year) === year && year % 1 === 0) {
									// release_date is a year ->
									// string
									Course.release_date = year.toString();
								} else {
									// release_date is full date,
									// so give $scope.Course a date
									// type field
									Course.release_date_as_date = new Date(
											data.release_date);
								}
								$scope.Course = Course;
							});

							$scope.updateCourse = function() {
								if ($scope.Course.release_date_as_date) {
									$scope.Course.release_date = $filter('date')
											(
													$scope.Course.release_date_as_date,
													'longDate');

								}
								$scope.Course.$update(function() {
									$state.go('courses');
								});
							};

						} ])
						
	.directive('cdToggleOnClick', function () 
			{
		  return {
		        restrict: 'A',
				scope: 
				{
					cdToggleOnClick: '='
				},
		        link: function(scope, element, attrs) {
					element.bind('click', function () {
		        if (scope.cdToggleOnClick == true)
						{
						  scope.cdToggleOnClick = false; 
						}
						else
						{
						  scope.cdToggleOnClick = true;
						}
						scope.$apply();
		      });
		    }
		  }
		});				
