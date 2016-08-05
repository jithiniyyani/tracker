app.controller('ProfileController',function($scope,LoginService,$http){

  //get user object from service
  $scope.user =  LoginService.getUser();

  $scope.loginStatus = function() {
  		return LoginService.loginStatus();
  };
});
