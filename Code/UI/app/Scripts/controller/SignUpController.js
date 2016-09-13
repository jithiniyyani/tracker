app.controller('SignUpController', function($scope, $http, $uibModal, $window, LoginService) {


    $scope.user = {};
    $scope.countryList = {};

    $http.get("http://mylostcar.com/StolenVehicle/countries").then(function(response) {
        $scope.countryList = response.data;
    }, function(data) {

    });

    $scope.signUp = function(user) {


        //initialize value
        $scope.user.email_notification = true;
        $scope.user.termsAndCondition = true;
        var modalRequest = {};
        modalRequest.method = 'post';
        modalRequest.url = 'http://mylostcar.com/StolenVehicle/register';
        modalRequest.payLoad = $scope.user;
        modalRequest.entityAttribute = 'user';
        modalRequest.successMessage = 'User registration was successful. Kindly activate your account by clicking on the link in the email';
        modalRequest.modalTime = 5000;
        modalRequest.modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'dialog/loader.html',
            controller: 'ModalController',
            size: 'md',
            resolve: {
                request: modalRequest
            }

        });

        modalRequest.modalInstance.result.then(function(result) {

          //nothing do do here


        }, function() {




        });
        LoginService.setLoginStatus(false);
        LoginService.setUser(null);
        $window.location = '/#/landing';
    };

    $scope.placeMarker = function(e) {
        console.log(e.latLng.lat() + " " + e.latLng.lng());
        var ll = e.latLng;
        $scope.positions = [];
        $scope.positions.push({
            lat: ll.lat(),
            lng: ll.lng()
        });
        $scope.user.addressCordinates = "[" + e.latLng.lat() + "," + e.latLng.lng() + "]";
    };

});
