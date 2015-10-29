/**
 * Created by KrishnaReddy on 10/16/2015.
 */
'use strict';

angular.module('myApp').controller('SignupCtrl', SignupCtrl);

SignupCtrl.$inject = [ 'UserService', '$location', '$rootScope',
		'FlashService', '$state' ];
function SignupCtrl(UserService, $location, $rootScope, FlashService, $state) {
	var vm = this;

	vm.register = register;
	vm.cancel = cancel;
	function cancel() {
		$state.go('signup');
	}

	function phoneNumberPattern() {
		var regexp = /^\(?(\d{3})\)?[ .-]?(\d{3})[ .-]?(\d{4})$/;
		return {
			test : function(value) {
				if ($scope.requireTel === false) {
					return true;
				}
				return regexp.test(value);
			}
		};
	}
	;

	function register() {
		vm.dataLoading = true;
		UserService.Create(vm.user).then(function(response) {
			if (response.success) {
				FlashService.Success('Registration successful', true);
				$state.go('success');
			} else {
				FlashService.Error(response.message);
				vm.error = "oops user registration has failed...";
				vm.dataLoading = false;
			}
		});
	}
}