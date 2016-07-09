app.controller('imageController', function($rootScope,$scope, $http, $uibModalInstance,
		loginService) {

	$scope.message = "Car Image";

	$scope.image;
	
	$scope.getImage = function(){
	
		$http.get("http://localhost:8080/findMyCar/image/" + $rootScope.data).then(function(response) {
			
			$scope.image = response.data
			
		});
	}
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

});