app.controller('vehicleSearchController', function($scope, $http) {

	var _selected;
	$scope.label_manufacturer = "Manufacturer";
	$scope.label_model = "Model";
	$scope.label_color = "Color";
	$scope.label_registrationNumber = "Registration Number";
	$scope.label_stolen = "Is Stolen";
	$scope.label_delete = "Delete";
	$scope.label_seeImage = "See Image";
	$scope.label_registrationDate = "Registration Date";
	$scope.label_notifyOwner = "Notifiy Owner"
	$scope.label_vehicleSearch = "Enter vehicle number";
	$scope.selectVehicle = undefined;
	// Any function returning a promise object can be used to load values asynchronously
	$scope.getLocation = function(val) {
		return $http.get('http://localhost:8080/findMyCar/getStolenCars', {
			params : {
				regNumber : val
				
			}
		}).then(function(response) {
			return response.data.cars; 
		});
	};


});