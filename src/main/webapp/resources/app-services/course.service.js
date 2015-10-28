(function () {
    'use strict';

    angular
        .module('myApp')
  .factory('Course', ['$resource', function ($resource) {
    // RESTful API
    return $resource('http://localhost:8080/eLearning/webservices/courses/:id',
        { id: '@id' },       
        { 
        	update: { method: 'PUT' },
          	remove: { method: 'DELETE' }
        });
  }])})();