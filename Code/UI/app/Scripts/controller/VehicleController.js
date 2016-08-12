app.controller('VehicleController',function($scope,LoginService,$http){

  //get user object from service
  $scope.vehicles = {};
  $scope.hasVehilces = false;
  $scope.countryList = {};
  $scope.vehicles =   $http.get("http://localhost/StolenVehicle/vehicles").then(function(data) {
          $scope.vehicles = data.data.vehicles;
          $scope.hasVehilces = true;
      }, function(data) {
          $scope.hasVehilces = false;
  });
  $http.get("http://localhost/StolenVehicle/countries").then(function(response) {
          $scope.countryList = response.data;
      }, function(data) {

    });
});
