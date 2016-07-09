app.controller("signUpController", function($scope, $http, $uibModalInstance,loginService) {

	$scope.signUp = "Sign Up";
	$scope.userId = "User Id";
	$scope.userName = "User Name";
	$scope.emailAddress = "Email Address";
	$scope.password = "Password";
	$scope.reTypePassword = "Retype Password";
	$scope.gender = "Gender";
	$scope.passport = "Passport";
	$scope.contactNumber = "Contact Number";
	$scope.termsAndConditions = "Accept terms and conditions";

	$scope.registerUser = function(user) {
	
		$http.post("http://localhost:8080/findMyCar/register", {
			"user" : user
		}).then(function(data) {

			loginService.setUser(data.data.user);
			loginService.setLoginStatus(true);
			$uibModalInstance.close();

		}, function(data) {
			loginService.setUser(null);
			loginService.setLoginStatus(false);			
		}

		);
	};

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

});