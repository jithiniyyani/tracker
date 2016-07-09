app.controller('settingsPageController', function($scope, $http,loginService) {


	$scope.user = null;
	$scope.cars = null;
	//all labels go here
	$scope.label_manufacturer = "Manufacturer";
	$scope.label_model = "Model";
	$scope.label_color = "Color";
	$scope.label_registrationNumber = "Registration Number";
	$scope.label_stolen = "Is Stolen";
	$scope.label_delete = "Delete";
	$scope.label_seeImage = "See Image";
	$scope.label_registrationDate = "Registration Date";

	$scope.getCars = function() {

		$scope.cars = null;
		$http.get("http://localhost:8080/findMyCar/cars").then(function(data) {

			$scope.cars = data.data.cars;

		}, function(data) {

			$scope.cars = null;
		}

		);
	}

	$scope.getUser = function() {

		$http.get("http://localhost:8080/findMyCar/user").then(function(data) {

			$scope.user = data.data.user;
		}, function(data) {
			$scope.user = null;
		});
	};

	
});