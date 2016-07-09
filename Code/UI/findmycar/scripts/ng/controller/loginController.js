app.controller('loginController', function($scope, $http, $uibModalInstance,
		loginService) {

	$scope.loginMessage = "Login";
	$scope.userId = "User Id";
	$scope.password = "Password";
	$scope.errorMessageLabel = "";

	$scope.login = function(user) {

		$http.post("http://localhost:8080/findMyCar/login", {
			"user" : user
		}).then(function(data) {

			loginService.setUser(data.data.user);
			loginService.setLoginStatus(true);
			$uibModalInstance.close();

		}, function(data) {
			loginService.setUser(null);
			loginService.setLoginStatus(false);
			$scope.errorMessageLabel = "Username or password is invalid";
		}

		);

	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

});