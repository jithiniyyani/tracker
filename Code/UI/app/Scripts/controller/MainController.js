app.controller('MainController',function($scope,LoginService,$http){

//all labels section go here
  $scope.homePageLabel = "Find My Stolen Vehicle";
  $scope.profileLabel = "My Profile";
  $scope.vehiclesLabel = "My Vehicles";
  $scope.alertsLabel = "Alerts";
  $scope.disclaimerLabel = "Disclaimer";
  $scope.termsAndConditionsLabel = "Terms and Conditions";
  $scope.signUpLabel = "Sign Up";
  $scope.loginLabel = "Login";
  $scope.logoutLabel = "Logout";


  $scope.loginStatus = function() {
  		return LoginService.loginStatus();
  };

  $scope.logout = function() {
  	$http.post("http://localhost/StolenVehicle/logout").then(
				function(data) {
					LoginService.setLoginStatus(false);
				}, function(data) {
					LoginService.setLoginStatus(false);
				}
	  );
	};
});
