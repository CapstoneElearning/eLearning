/**
 * Created by KrishnaReddy on 10/16/2015.
 */
            'use strict';
            angular.module('myApp').controller('ForgotPwdCtrl', ForgotPwdCtrl);

            ForgotPwdCtrl.$inject = ['$location', 'AuthenticationService', 'FlashService'];
            function ForgotPwdCtrl($location, AuthenticationService, FlashService) {
                var vm = this;

                vm.resetPwd = resetPwd;
                
                function resetPwd() {
                    vm.dataLoading = true;
                    UserService.Create(vm.user)
                        .then(function (response) {
                            if (response) {
                                FlashService.Success('Registration successful', true);
                                $state.go('signup.success');
                            } else {
                                FlashService.Error(response.message);
                                vm.dataLoading = false;
                            }
                        });
                }
               

                function login() {
                    vm.dataLoading = true;
                    AuthenticationService.Login(vm.username,vm.password, function (response) {
                        if (response) {
                            AuthenticationService.SetCredentials(vm.username, vm.password);
                            $location.path('/');
                        } else {
                            FlashService.Error(response.message);
                            vm.dataLoading = false;
                        }
                    });
                };
            };

