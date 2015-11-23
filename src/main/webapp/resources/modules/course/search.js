/**
 * Created by KrishnaReddy on 10/16/2015.
 */
'use strict';

angular.module('myApp').controller('SearchCtrl', SearchCtrl);
SearchCtrl.$inject = [ 'UserService', '$location', '$rootScope','FlashService', '$state' ];
function SearchCtrl(UserService, $location, $rootScope, FlashService, $state) {
	var vm = this;

	vm.searchCourses = searchCourses;
	vm.cancel = cancel;
	function cancel() {
		$state.go('signup');
	}

	function searchCourses() {
		vm.dataLoading = true;
		UserService.fetchCourses(vm.searchQuery).then(function(response) {
			if (response.success) {
				$state.go('dashboard');
			} else {
				FlashService.Error(response.message);
				vm.error = "oops user registration has failed...";
				vm.dataLoading = false;
			}
		});
	}
}