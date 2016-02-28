var searchApp = angular.module('searchApp', []);

searchApp.controller('UserCtrl', function ($scope, $http) {

  $scope.search = function() {
	  $http.post("cs580/user/" + $scope.searchInput)
	  	.success(function(data){
	  		$scope.loadUsers();
	  	});
  }

});