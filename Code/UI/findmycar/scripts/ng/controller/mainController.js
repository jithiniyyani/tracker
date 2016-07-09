app.controller("mainController", function($scope, $http, loginService) {

	//all labels go here
	$scope.homePageLabel = "FindMyVehicle";
	$scope.landingPageMessage = "We help you find lost vehicles";
	$scope.landingPageJumboTronMessage = "Register with us and protect your vehicle and stand a chance to win big money";
    
	$scope.page = "views/landingPage.html";

	$scope.loggedIn = false;

	$scope.opentab = function(location) {

		$scope.page = location;

	};

	$scope.loginStatus = function() {

		return loginService.loginStatus();
	};

	$scope.logout = function() {

		$scope.page = "views/landingPage.html";

		$http.post("http://localhost:8080/findMyCar/logout").then(
				function(data) {

					loginService.setLoginStatus(false);

				}, function(data) {

					loginService.setLoginStatus(false);
				}

		);

	};

	$scope.getStolenLocationList = function() {

		$http.get("http://localhost:8080/findMyCar/getLostLocation").then(
				function(data) {

					console.log(data);
					$scope.locationList = data.data.stolenLocations;

				}, function(data) {

					$scope.locationList = null;
				});

	};
});
