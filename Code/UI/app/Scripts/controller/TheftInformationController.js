app.controller('TheftInformationController', function($scope, $http, $uibModal) {

     $scope.theft_info = {};
     $scope.theft_info.vehicle = {};
     $scope.request={};

     $scope.registerTheft = function(theft_info) {

       //do init here
        $scope.theft_info.vehicle.stolen = true;
        $scope.theft_info.status = 'LOST';
        $scope.request.method = 'post';
        $scope.request.url = 'http://localhost/StolenVehicle/registerTheft';
        $scope.request.payLoad = $scope.theft_info;
        $scope.request.entityAttribute = 'theft_info';
        $scope.modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'dialog/loader.html',
            controller: 'ModalController',
            size: 'md',
            resolve: {
                request: $scope.request
            }

        });
        $scope.request.modalInstance = $scope.modalInstance;
        $scope.modalInstance.result.then(function(result) {

        }, function() {

        });
    };

    $scope.placeMarker = function(e) {
      console.log(e.latLng.lat() + " " + e.latLng.lng());
     var ll = e.latLng;
     $scope.positions=[];
     $scope.positions.push({lat:ll.lat(), lng: ll.lng()});
     $scope.theft_info.theft_location_cordinates = "[" + e.latLng.lat() + "," + e.latLng.lng() + "]";
  };

});
