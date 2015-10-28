/**
 * Created by KrishnaReddy on 10/16/2015.
 */
'use strict';

angular.module('myApp').controller('SignupCtrl', SignupCtrl);

SignupCtrl.$inject = ['UserService', '$location', '$rootScope', 'FlashService',];
function SignupCtrl(UserService, $location, $rootScope, FlashService) {
    var vm = this;

    vm.register = register;
    vm.cancel = cancel;
    function cancel(){
    	$location.path('/login');
    	
    }
    
    function phoneNumberPattern(){
        var regexp = /^\(?(\d{3})\)?[ .-]?(\d{3})[ .-]?(\d{4})$/;
        return {
            test: function(value) {
                if( $scope.requireTel === false ) {
                    return true;
                }
                return regexp.test(value);
            }
        };
    };
    
    function register() {
        vm.dataLoading = true;
        UserService.Create(vm.user)
            .then(function (response) {
                if (response) {
                    FlashService.Success('Registration successful', true);
                    $location.path('/success');
                } else {
                    FlashService.Error(response.message);
                    vm.dataLoading = false;
                }
            });
    }
}