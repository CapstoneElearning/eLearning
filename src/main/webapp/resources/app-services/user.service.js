(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('UserService', UserService);

    UserService.$inject = ['$http'];
    function UserService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Create = Create;
        service.Login = Login;
        service.Update = Update;
        service.Delete = Delete;
        service.resetPwd = resetPwd;

        return service;

        function GetAll() {
            return $http.get('/eLearning/webservices/users').then(handleSuccess, handleError);
        }

        function GetById(id) {
            return $http.get('/eLearning/webservices/user/' + id).then(handleSuccess, handleError);
        }

        function Login(uname,pwd,callback) {
            return $http.post('/eLearning/webservices/userlogin', {username:uname,password:pwd})
                .then(handleSuccess, handleError);
        }

        function GetByUsername(username) {
            return $http.get('/eLearning/webservices/user/' + username).then(handleSuccess, handleError);
        }

        function Create(user) {
            return $http.post('/eLearning/webservices/user', user).then(handleSuccess, handleError);
        }
        
        function resetPwd(username) {
            return $http.post('/eLearning/webservices/user/', username).then(handleSuccess, handleError);
        }


        function Update(user) {
            return $http.put('/eLearning/webservices/user/' + user.id, user).then(handleSuccess, handleError);
        }

        function Delete(id) {
//            return $http.delete('/eLearning/webservices/user/' + id).then(handleSuccess, handleError);
        }

        // private functions

        function handleSuccess(res) {
            return { success: true, data: res.data , message : res.status};
        }

        function handleError(error) {
        	console.log("error obj: "+JSON.stringify(error));
                return { success: false, message: error.statusText};
        }
    }

})();
