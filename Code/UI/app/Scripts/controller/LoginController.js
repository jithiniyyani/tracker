app.controller('LoginController',function($scope, $http,LoginService,UrlService){

//all labels section go here
  $scope.lc_mainMessage = "Please login to repot your lost vehicle";
  $scope.lc_emailAddressLabel = "Email Address";
  $scope.lc_passowrd = "Password";
  $scope.lc_submit = "Login";
  $scope.lc_register = "Register";
  $scope.lc_forgotPassword = "Forgot Password";

  $scope.emailaddress = "";
  $scope.password = "";

  $scope.appLogin = function(user) {

    $http.post(UrlService.loginUrl, {
      "user" : user
    }).then(function(data) {

      LoginService.setUser(data.data.user);
      LoginService.setLoginStatus(true);


    }, function(data) {
      LoginService.setUser(null);
      LoginService.setLoginStatus(false);
      $scope.errorMessageLabel = "Username or password is invalid";
    }

    );

  };

});
