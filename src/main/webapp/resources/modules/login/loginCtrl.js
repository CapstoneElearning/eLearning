/**
 * Created by KrishnaReddy on 10/16/2015.
 */
'use strict';
angular.module('myApp').controller('LoginCtrl', LoginCtrl);

LoginCtrl.$inject = [ 'UserService', '$location', 'AuthenticationService',
		'FlashService', '$state' ];
function LoginCtrl(UserService, $location, AuthenticationService, FlashService,
		$state) {
	var vm = this;

	vm.login = login;

	(function initController() {
		// reset login status
		AuthenticationService.ClearCredentials();
	})();

	function login() {
		vm.dataLoading = true;
		UserService.Login(vm.username, vm.password).then(function(response) {
			if (response.success) {
				FlashService.Success('Registration successful', true);
				AuthenticationService.SetCredentials(vm.username, vm.password);
				$state.go('dashboard');
			} else {
				FlashService.Error("Invalid username or password");
				vm.error = "Invalid username or password";
				vm.dataLoading = false;
			}
		});
	}
	;

};

