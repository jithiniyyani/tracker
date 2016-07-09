app.controller('carController', function($scope, $http, $uibModalInstance,
		loginService) {

	
	$scope.manufacturer = "Vechile manufacturer";
	$scope.model = "Vechile Model";
	$scope.color = "Vechile Color";
	$scope.stolen = "Was the car stolen"
	$scope.registrationNo = "Car registration number"
	$scope.cars;
	$scope.errorMessageLabel = "";

	$scope.addCar = function(car) {

		$http.post("http://localhost:8080/findMyCar/car", {
			"car" : car
		}).then(function(data) {

			$uibModalInstance.close();

		}, function(data) {
			
			$scope.errorMessageLabel = "Could not add car";
		}

		);

	};
	


	$scope.getCars = function() {
		
		$scope.cars = null;
		$http.get("http://localhost:8080/findMyCar/cars").then(function(data) {
			
			$scope.cars = data.data.cars;
			
		}, function(data) {

			$scope.cars  = null;
		}

		);
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

});