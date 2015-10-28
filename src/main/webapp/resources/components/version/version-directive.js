'use strict';

angular.module('myApp.version.version-directive', [])
.directive("passwordMatch", function() {
  return {
    require: "ngModel",
    scope: {
      passwordMatch: '='
    },
    link: function(scope, element, attrs, ctrl) {
      scope.$watch(function() {
        var combined;
        if (scope.passwordMatch || ctrl.$viewValue) {
          combined = scope.passwordMatch + '_' + ctrl.$viewValue;
        }
        return combined;
      }, function(value) {
        if (value) {
          ctrl.$parsers.unshift(function(viewValue) {
            var origin = scope.passwordMatch;
            if (origin !== viewValue) {
              ctrl.$setValidity("passwordMatch", false);
              return undefined;
            } else {
              ctrl.$setValidity("passwordMatch", true);
              return viewValue;
            }
          });
        }
      });
    }
  };
})

.directive("calAge",function() {

  var findAge = function (dateVal) {
    var todayDate = new Date();
    var todayYear = todayDate.getFullYear();
    var todayMonth = todayDate.getMonth();
    var todayDay = todayDate.getDate();
    var age = todayYear - dateVal.getFullYear();

    if (todayMonth < birthMonth - 1) {
      age--;
    }

    if (birthMonth - 1 == todayMonth && todayDay < birthDay) {
      age--;
    }
    return age;
  }

});