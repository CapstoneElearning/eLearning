/**
 * Created by KrishnaReddy on 10/16/2015.
 */
'use strict';
angular.module('myApp')

.controller(
		'CourseCtrl',
		[ '$scope', '$state', '$window', 'Course',
				function($scope, $state, $window, Course) {

					// returns a promise that will bind
					// to view as soon as available
					$scope.courses = Course.query(function(data) {
						// console.log(data);
					});

					$scope.deleteCourse = function(index) {
						if ($window.confirm('Are you sure?')) {
							$scope.courses[index].$delete(function() {
								// delete locally
								$scope.courses.splice(index, 1);
							});
						}
					};
				} ])

// New
.controller(
		'NewCourseCtrl',
		[
				'$scope',
				'$state',
				'$filter',
				'$stateParams',
				'Course',
				function($scope, $state, $filter, $stateParams, Course) {

					// $scope.Course (bound to the form)
					// has release_date as date type
					// $scope.newCourse converts it to
					// string for POSTing
					$scope.Course = {};
					$scope.newCourse = new Course();

					$scope.addCourse = function() {
						$scope.newCourse.author = $scope.Course.author;
						$scope.newCourse.title = $scope.Course.title;
						$scope.newCourse.image = $scope.Course.image;
						// convert date to string
						$scope.newCourse.release_date = $filter('date')(
								$scope.Course.release_date_as_date, 'longDate');
						$scope.newCourse.$save(function() {
							$state.go('courses');
						});
					};
				} ])

// Show
.controller(
		'ShowCourseCtrl',
		[ '$scope', '$stateParams', 'Course',
				function($scope, $stateParams, Course) {

					$scope.Course = Course.get({
						id : parseInt($stateParams.id, 10)
					}, function() {
						// console.log($scope.Course);
					});

				} ])

// / Edit
.controller(
		'EditCourseCtrl',
		[
				'$scope',
				'$state',
				'$filter',
				'$stateParams',
				'Course',
				function($scope, $state, $filter, $stateParams, Course) {

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
							$scope.Course.release_date = $filter('date')(
									$scope.Course.release_date_as_date,
									'longDate');
							
						}
						$scope.Course.$update(function() {
							$state.go('courses');
						});
					};

				} ]);
