app.controller("CarController", ['LoginService','$log', '$http' ,function(LoginService,$log, $http) {

	var self = this;
	self.car = null;
	self.selectedCar = null;
	self.cars = [];
	self.error_code = "";
	self.error_message = "";
	
	$http.get("/findMyCar/cars").then(function(response) {
		self.cars = response.data.cars;
		LoginService.setLoggIn(true);		
	}, function(error_response) {		
		self.error_message = error_response;		
	});
	
	self.setSelectedCar = function(selectedCar) {
		self.car = selectedCar;
	    console.log(self.car);
	}
		
	self.addCar = function() {

		$http.post("/findMyCar/car",self.car).then(function(response) {
			$log.log('Added car successfully');
			self.getCars();
		}, function error() {
			$log.log('Failed car successfully')
		});
	};
	
	self.deleteCar = function() {

		$log.log(self.car);
		$http.delete("/findMyCar/car/" + self.car.carId).then(function(response) {
			$log.log('Deleted Car car successfully');
			self.getCars();
		}, function error() {
			$log.log('Failed car successfully')
		});
	};
	
	self.getCars = function(){
		
		$http.get("/findMyCar/cars").then(function(response) {
			self.cars = response.data.cars;
			LoginService.setLoggIn(true);		
		}, function(error_response) {		
			self.error_message = error_response;		
		});
	}

} ])