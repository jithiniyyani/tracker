app.controller('FindController',function($scope,$http,$uibModal){


  $scope.countryList = {};
  $scope.vehicleTypeList = {};
  $scope.vehicleMakeList = {};
  $scope.vehicleModelList = {};
  $scope.vehicleList = {};
  $scope.countryCode = {};
  $scope.vehicleType = {};
  $scope.vehicleMake = {};
  $scope.request = {};

  $scope.countryCode = null;
  $http.get("http://localhost/StolenVehicle/countries").then(function(response) {
          $scope.countryList = response.data;
      }, function(data) {

    });

    $scope.getVehicleTypes = function(){

      var url = "http://localhost/StolenVehicle/vehiclesTypes?countryId=" + $scope.countryCode;
      $http.get(url).then(function(response) {
              $scope.vehicleTypeList = response.data;
          }, function(data) {

        });

    };

    $scope.getVehicleMakeList = function(){

      var url = "http://localhost/StolenVehicle/vehicleMake?countryId=" + $scope.countryCode + "&vehicleType=" + $scope.vehicleType;
      $http.get(url).then(function(response) {
              $scope.vehicleMakeList = response.data;
          }, function(data) {

        });

    };

    $scope.getVehicleModelList = function(){

      var url = "http://localhost/StolenVehicle/vehicleModel?countryId=" + $scope.countryCode + "&vehicleType=" + $scope.vehicleType + "&vehicleMake=" + $scope.vehicleMake;
      $http.get(url).then(function(response) {
              $scope.vehicleModelList = response.data;
          }, function(data) {

        });

    };

    $scope.searchForStolenVehicles = function(){

        $http.post("http://localhost/StolenVehicle/searchForStolenVehicles",{}).then(
          function(response) {
                $scope.theftInfo = response.data;
          }, function(data) {

          }
      );

    };

    $scope.showTheftInfo = function(){

      $scope.request.method = 'show';
      $scope.request.message = 'We will be showing you details here';
      $scope.request.modalTime = -1;
      $scope.request.modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'dialog/viewtheft.html',
            controller: 'VehicleViewController',
            size: 'lg',
            resolve: {
                request: $scope.theftInfo
            }

        });

    };

});
