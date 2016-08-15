app.controller('TheftInformationController', function($scope, $http, $uibModal,LoginService,$location) {

     $scope.upload = "fragments/upload.html";
     $scope.theft_info = {};
     $scope.theft_info.vehicle = {};
     $scope.search = {};
     $scope.registrationNumber = null;
     $scope.user = LoginService.getUser();
     $scope.theft_info_view = null;

     $scope.registerTheft = function(theft_info) {

       //do init here
        $scope.theft_info.vehicle.stolen = true;
        $scope.theft_info.status = 'LOST';
        $scope.theft_info.theft_dateTime = $scope.picker3.date;
        $scope.theft_info.vehicle.user_id = $scope.user.id;
        $scope.theft_info.vehicle.country_id = $scope.user.country_id;

        var modalRequest = {};
        modalRequest.method = 'post';
        modalRequest.url = 'http://localhost/StolenVehicle/registerTheft';
        modalRequest.successMessage = 'Theft has been sucessfully registered. You will get an alert if some one finds it. Until then relax';
        modalRequest.modalTime = 3000;
        modalRequest.payLoad = $scope.theft_info;
        modalRequest.entityAttribute = 'theft_info';
        modalRequest.modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'dialog/loader.html',
            controller: 'ModalController',
            size: 'md',
            resolve: {
                request: modalRequest
            }

        });
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

$scope.getTheftInfoById = function() {

    var theft_info_id = $location.search().theftId;
    $scope.theft_info_view = $http.get("http://localhost/StolenVehicle/getTheftInfo?theftId=" + theft_info_id).then(function(response) {
        $scope.theft_info_view = response.data.theft_info;
    }, function(data) {
        $scope.theft_info_view = null;
    });
};

 $scope.searchForStolenVehicle = function(search){

    $scope.request = {};
    $scope.request.method = 'post';
    $scope.request.message = "Please wait as we search for a match";
    $scope.request.modalTime = 2000;
    $scope.request.payLoad = search;
    $scope.request.entityAttribute = 'search';
    $scope.request.url = 'http://localhost/StolenVehicle/searchForTheft';
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

 // date and time picker
 $scope.picker3 = {
     date: new Date()
 };

 $scope.openCalendar = function(e, picker) {
     $scope[picker].open = true;
 };


});
