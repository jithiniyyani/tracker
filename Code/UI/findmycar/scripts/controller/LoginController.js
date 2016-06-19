app.controller("LoginController", [
		'LoginService',
		'$log',
		'$http',
		function(LoginService, $log, $http) {

			// yet to understand this
			var self = this;

			self.message = "This message comes from homePageController";

			self.isLoggedIn = function() {

				return LoginService.isLoggedIn();
			}

			self.login = function() {

				$http.post("/findMyCar/login", self.user).then(
						function(response) {
							LoginService.setLoggIn(true);
						}, function error() {
							LoginService.setLoggIn(false);
						});
			}

			self.logout = function() {

				$http.post("/findMyCar/logout").then(function(response) {
					LoginService.setLoggIn(false);
				}, function error() {
					LoginService.setLoggIn(false);
				});
			}
			
		} ]);