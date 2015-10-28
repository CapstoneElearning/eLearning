/**
 * Created by KrishnaReddy on 10/16/2015.
 */
            'use strict';
            angular.module('myApp').controller('LoginCtrl', LoginCtrl);

            LoginCtrl.$inject = ['$location', 'AuthenticationService', 'FlashService'];
            function LoginCtrl($location, AuthenticationService, FlashService) {
                var vm = this;

                vm.login = login;

                (function initController() {
                    // reset login status
                    AuthenticationService.ClearCredentials();
                })();

                function login() {
                    vm.dataLoading = true;
                    AuthenticationService.Login(vm.username,vm.password, function (response) {
                        if (response.success) {
                            AuthenticationService.SetCredentials(vm.username, vm.password);
                            $location.path('/');
                        } else {
                            FlashService.Error(response.message);
                            vm.dataLoading = false;
                        }
                    });
                };
            };

