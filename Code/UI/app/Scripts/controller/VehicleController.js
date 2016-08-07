app.controller('VehicleController',function($scope,LoginService,$http){

  //get user object from service
  $scope.vehicles = {};
  $scope.hasVehilces = false;
  $scope.vehicles =   $http.get("http://localhost/StolenVehicle/vehicles").then(function(data) {
          $scope.vehicles = data.data.vehicles;
          $scope.hasVehilces = true;
      }, function(data) {
          $scope.hasVehilces = false;
      });
});
