app.controller('SignUpController', function($scope, $http, $uibModal) {


    $scope.user = {};

    $scope.signUp = function(user) {

        $scope.request = {};
        $scope.request.method = 'post';
        $scope.request.url = 'http://localhost/StolenVehicle/register';
        $scope.user.email_notification = true;
        $scope.user.termsAndCondition = true;
        $scope.request.payLoad = $scope.user;
        $scope.request.entityAttribute = 'user';
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

          LoginService.setLoginStatus(false);
          $window.location='/app/#/landing';

        }, function() {
          LoginService.setLoginStatus(false);
          LoginService.setUser(null);
          $window.location='/app/#/landing';

        });
    };

    $scope.placeMarker = function(e) {
      console.log(e.latLng.lat() + " " + e.latLng.lng());
     var ll = e.latLng;
     $scope.positions=[];
     $scope.positions.push({lat:ll.lat(), lng: ll.lng()});
     $scope.user.addressCordinates = "[" + e.latLng.lat() + "," + e.latLng.lng() + "]";
  };

});
