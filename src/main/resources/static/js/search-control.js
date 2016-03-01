var searchApp = angular.module('searchApp', []);

searchApp.controller('UserCtrl', function ($scope, $http) {

  $scope.loadPage = function(){
	  $http.get("/search/taipei")
	  	.success(function(data){
	  		$scope.pageContent = data;
	  	});
	  	
	  	
	 
  }


  $scope.loadPage();
});