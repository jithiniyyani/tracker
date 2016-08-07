app.controller('TheftInformationController', function($scope, $http, $uibModal,LoginService,$location) {

     $scope.theft_info = {};
     $scope.theft_info.vehicle = {};
     $scope.request={};
     $scope.registrationNumber = null;
     $scope.user = LoginService.getUser();
     $scope.theft_info_view = {};
     $scope.registerTheft = function(theft_info) {

       //do init here
        $scope.theft_info.vehicle.stolen = true;
        $scope.theft_info.status = 'LOST';
        $scope.theft_info.vehicle.user_id = $scope.user.id;
        $scope.theft_info.vehicle.country_id = $scope.user.country_id;
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

 $scope.getTheftStatusByVehicleRegNumber = function(registrationNumber){

    $scope.request = {};
    $scope.request.method = 'get';
    $scope.request.url = 'http://localhost/StolenVehicle/searchForTheft?regNumber=' + registrationNumber;
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

         $scope.theft_info_view  = result.theft_info;

       }, function() {

       });

 }

 $scope.getTheftInfoById = function(){
   //assign it here
    //$scope.theft_info_view = {};
      $scope.theft_info_id = $location.search().theftId;
      $scope.theft_info_view =   $http.get("http://localhost/StolenVehicle/getTheftInfo?theftId=" + $scope.theft_info_id).then(function(response) {
              $scope.theft_info_view = response.data.theft_info;
          }, function(data) {

          });
 };

});
