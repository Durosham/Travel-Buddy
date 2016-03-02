var searchApp = angular.module('searchApp', []);

searchApp.controller('UserCtrl', function ($scope, $http, $log ) {

  $scope.loadPage = function(user_input){
	  $http.get("search/" + user_input)
	  	.success(function(data){
	  		$scope.pageContent = data;
	  	});
	  
  }
  
  $scope.loadPage("paris");
  
});