app.controller("UserController", [ '$log', '$http', function($log, $http) {

	var self = this;

	self.user = null;

	$http.get('/findMyCar/user').then(function(response) {
		var userResponse = response.data;
		self.user = userResponse.user;
	}, function(error) {
		$log.log("Got an error from the server");
	})
	
	self.registerUser = function() {

				$http.post("/findMyCar/register",self.user).then(function(response) {
					LoginService.setLoggIn(true);
				}, function error() {
					LoginService.setLoggIn(false);
				});
			}

} ]);