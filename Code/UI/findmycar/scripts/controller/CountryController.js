app.controller("CountryController", ['LoginService','$log', '$http', function(LoginService,$log, $http) {

	// yet to understand this
	var self = this;

	
	self.message = "This message comes from CountryController";

	self.countries = [];

	$http.get('/findMyCar/countries').then(function(response) {
		var countriesReponse = response.data;
		self.countries = countriesReponse.countries;
		LoginService.setLoggIn(true);
	}, function(error) {
		$log.log("Got an error from the server");
	})

} ]);