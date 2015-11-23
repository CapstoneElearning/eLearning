(function () {
    'use strict';

    angular
        .module('myApp')
        .factory('CourseService', CourseService);

    CourseService.$inject = ['$http'];
    function CourseService($http) {
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByName = GetByName;
        service.Create = Create;
        service.Update = Update;
        service.Delete = Delete;
        service.enorollCourse = enorollCourse;

        return service;

        function GetAll() {
            return $http.get('/eLearning/webservices/course/list').then(handleSuccess, handleError);
        }

        function GetById(id) {
            return $http.get('/eLearning/webservices/course/' + id).then(handleSuccess, handleError);
        }

        function GetByName(name) {
            return $http.get('/eLearning/webservices/course/' + name).then(handleSuccess, handleError);
        }

        function Create(user) {
            return $http.post('/eLearning/webservices/course', user).then(handleSuccess, handleError);
        }
       

        function Update(user) {
            return $http.put('/eLearning/webservices/course/' + user.id, user).then(handleSuccess, handleError);
        }
        
        function enorollCourse(courseId,user) {
            return $http.put('/eLearning/webservices/course/enroll/'+ courseId, user.id).then(handleSuccess, handleError);
        }

        function Delete(id) {
//            return $http.delete('/eLearning/webservices/course/' + id).then(handleSuccess, handleError);
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
