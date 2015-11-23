/**
 * Created by KrishnaReddy on 10/16/2015.
 */
(function() {
	'use strict';

	angular.module('myApp').controller('dashboardCtrl', dashboardCtrl)

	.directive('showTab', function() {
		return {
			restrict : 'A',
			link : function(scope, element, attrs) {
				element.bind('click', function(e) {
					e.preventDefault();
					$('#myTab a:first').tab('show');
					$(element).tab('show');
				});
			}
		};
	})

	.filter('role', function() {
		
		return function(role) {

			if (role === 1) {
				return "Student";
			} else if (role === 2) {
				return "Instructor";
			} else if (role === 3) {
				return "Admin";
			}

		};
	});

	dashboardCtrl.$inject = [ 'UserService', 'CourseService', '$rootScope' ];
	function dashboardCtrl(UserService, CourseService, $rootScope) {
		var vm = this;
		vm.user = null;
		vm.allUsers = [];
		vm.allCourses = [];
		vm.deleteUser = deleteUser;
		initController();

		$(function() {
			$('#myTab a:first').tab('show');
			$('a[data-toggle="tab"]').on('shown', function(e) {
				e.target // activated tab
				e.relatedTarget // previous tab
			})
		})

		function initController() {
			loadCurrentUser();
			loadAllUsers();
			loadAllCourses();

		}

		function loadCurrentUser() {
			UserService.GetByUsername($rootScope.globals.currentUser.username)
					.then(function(user) {
						vm.user = user.data;
					});
		}

		function loadAllUsers() {
			UserService.GetAll().then(function(users) {
				vm.allUsers = users.data;
			});
		}

		function loadAllCourses() {
			CourseService.GetAll().then(function(courses) {
				vm.allCourses = courses.data;
			});
		}

		function deleteUser(id) {
			UserService.Delete(id).then(function() {
				loadAllUsers();
			});
		}

		function deleteCourse(id) {
			CourseService.Delete(id).then(function() {
				loadAllCourses();
			});
		}

		function createCourse(id) {
			CourseService.create(id).then(function() {
				loadAllCourses();
			});
		}

		function updateCourse(id) {
			CourseService.create(id).then(function() {
				loadAllCourses();
			});
		}
	}

})();
