/**
 * Created by KrishnaReddy on 10/16/2015.
 */
'use strict';

angular.module('myApp').controller('SliderCtrl', SliderCtrl);
angular.module('myApp').directive('slider', function($timeout) {	
	return {
	    restrict: 'AE',
		replace: true,
		scope:{
			images: '='
		},
	    link: function (scope, elem, attrs) {
		
			scope.currentIndex=0;

			scope.next=function(){
				scope.currentIndex<scope.images.length-1?scope.currentIndex++:scope.currentIndex=0;
			};
			
			scope.prev=function(){
				scope.currentIndex>0?scope.currentIndex--:scope.currentIndex=scope.images.length-1;
			};
			
			scope.$watch('currentIndex',function(){
				scope.images.forEach(function(image){
					image.visible=false;
				});
				scope.images[scope.currentIndex].visible=true;
			});
			
			/* Start: For Automatic slideshow*/
			
			var timer;
			
			var sliderFunc=function(){
				timer=$timeout(function(){
					scope.next();
					timer=$timeout(sliderFunc,5000);
				},5000);
			};
			
			sliderFunc();
			
			scope.$on('$destroy',function(){
				$timeout.cancel(timer);
			});
			
			/* End : For Automatic slideshow*/
			
	    },
		templateUrl:'/eLearning/resources/modules/partials/banner.html'
	  }	
})

SliderCtrl.$inject = [ '$scope'];

function SliderCtrl($scope) {
    $scope.images=[
		{src:'img1.png',title:'Pic 1'},
		{src:'img2.png',title:'Pic 2'},
		{src:'img3.png',title:'Pic 3'},
		{src:'img4.png',title:'Pic 4'},
		{src:'img5.png',title:'Pic 5'}
	];
};
