'use strict';

angular.module('myApp.version.version-directive', [])

.directive('usernameAvailable', function($timeout, $q) {
  return {
    restrict: 'AE',
    require: 'ngModel',
    link: function(scope, elm, attr, model) { 
      model.$asyncValidators.usernameExists = function() { 
        //here you should access the backend, to check if username exists
        //and return a promise
        var defer = $q.defer();
        $timeout(function(){
          model.$setValidity('usernameExists', false); 
          defer.resolve;
        }, 1000);
        return defer.promise;
      };
    }
  } 
})

.directive('ensureUnique', function($timeout, $q, $http) {
	return {
	    restrict: 'A',
	    require: 'ngModel',
	    link: function (scope, element, attrs, ngModel) {
	      element.bind('blur', function (e) {
	        if (!ngModel || !element.val()) return;
	        var keyProperty = scope.$eval(attrs.ensureUnique);
	        var username = element.val();
	         $http.post("/eLearning/webservices/user/isunique", username).then(function (unique) {
	            //since the Ajax call was made
	            if (username == element.val()) {
	              console.log('unique = '+unique);
	              ngModel.$setValidity('unique', unique);
	              scope.$broadcast('show-errors-check-validity');
	            }
	          });
	      });
	    }
	  }
})

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

})

.directive('emailValidator', ['$http', function($http){
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl){

            ctrl.$parsers.unshift(function(viewValue){
                $http.get('api/profiles/asyncEmail/' + viewValue).success(function(data){
                    console.log(data);
                    if(data === 'true'){
                        ctrl.$setValidity('emailValidator', true);
                        return viewValue;
                    }else{
                        ctrl.$setValidity('emailValidator', false);
                        return undefined;
                    }
                }).error(function(data, status){
                        console.log(status);
                    });
            });
        }
    }
}])

.directive('fileUpload', function(){
    return{
        link: function(scope, elm, attrs){
            var opts = {};
            var config = {
                //runtimes : 'gears,html5,flash,silverlight,browserplus',
                runtimes: 'html5',
                browse_button : 'pickfiles',
                container: 'container',
                max_file_size : '10mb',
                url : '/gc/api/profiles/pics/1',
                resize : {width : 320, height : 240, quality : 90},
                flash_swf_url : '../js/plupload.flash.swf',
                silverlight_xap_url : '../js/plupload.silverlight.xap',
                filters : [
                    {title : "Image files", extensions : "jpg,gif,png"},
                    {title : "Zip files", extensions : "zip"}
                ]
            };

            if(attrs.fileUpload){
                opts = scope.$eval(attrs.fileUpload);
                if(opts.dropTarget){
                    config.drop_element = opts.dropTarget;
                }
            }

            function $(id) {
                return document.getElementById(id);
            }

            var uploader = new plupload.Uploader(config);

            /*uploader.bind('Init', function(up, params) {
             $('filelist').innerHTML = "<div>Current runtime: " + params.runtime + "</div>";
             });*/

            uploader.bind('FilesAdded', function(up, files) {
                for (var i in files) {
                    $('filelist').innerHTML += '<div id="' + files[i].id + '">' + files[i].name + ' (' + plupload.formatSize(files[i].size) + ') <b></b></div>';
                }

                setTimeout(function(){
                    uploader.start();
                }, 500);
            });

            uploader.bind('UploadProgress', function(up, file) {
                $(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            });

            uploader.bind('FileUploaded', function(uploader, file, response){
                console.log(uploader, file, response);
                $(config.drop_element).src = response.response;
            })

            /*$('uploadfiles').onclick = function() {
             uploader.start();
             return false;
             };*/

            uploader.init();
        }
    }
});