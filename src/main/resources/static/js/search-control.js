var searchApp = angular.module('searchApp', []);

searchApp.controller('UserCtrl', function ($scope, $http) {

  $scope.loadPage = function(user_input){
	  $http.get("search/" + user_input)
	  	.success(function(data){
	  		$scope.pageContent = data;
	  	});  
  }
  
  $scope.showPage = function(user_input)
  {
		$scope.loadPage(user_input);
  }
  
  $scope.goToNextPage = function () {
    window.location="#/search_results/country="+$scope.country+"&&state="+$scope.state;
}

  
});