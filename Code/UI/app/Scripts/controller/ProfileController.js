app.controller('ProfileController',function($scope,LoginService,$http){

  //get user object from service
  $scope.user =  LoginService.getUser();

});
