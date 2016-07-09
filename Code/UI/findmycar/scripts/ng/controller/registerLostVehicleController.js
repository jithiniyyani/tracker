app.controller('registerLostVehicleController', function($scope, $http,loginService) {

	$scope.user = null;
	$scope.cars = null;
    $scope.car = null;
	$scope.getUser = function() {

		$http.get("http://localhost:8080/findMyCar/user").then(function(data) {
			$scope.user = data.data.user;
		}, function(data) {
			$scope.user = null;
		});
	};

	
	$http.get("http://localhost:8080/findMyCar/cars").then(function(data) {

		$scope.cars = data.data.cars;
		
	}, function(data) {

		$scope.cars  = null;
	}
	);
	

	$scope.saveStolenLocationForCar = function(location) {

		console.log('saving' + location[0].lat + ' ' + location[0].lng
				+ 'for car with id ' + $scope.car.carId);

		$http.post("http://localhost:8080/findMyCar/saveLostLocation", {
			"stolenLocation" : {
				"id" : null,
				"carId" : $scope.car.carId,
				"locationCorindates" : location[0].lat + ',' + location[0].lng
			}
		}).then(function(data) {
			console.log(data);
		}, function(data) {
			console.log(data);
		});
	};
	
	$scope.showLostVehicleRegistrationDiv = function(){
		
	    var status = true;
	    if($scope.cars == null || $scope.cars == undefined || $scope.cars.length ==0){
	    	status = false;
	    }
		return status;
	}
	 
});