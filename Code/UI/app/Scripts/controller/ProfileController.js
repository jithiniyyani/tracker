app.controller('ProfileController', function($scope, LoginService, $http, $uibModal) {

    $http.get("http://mylostcar.com/StolenVehicle/user").then(function(response) {
        LoginService.setLoginStatus(true);
        LoginService.setUser(response.data.user);
    }, function(data) {
        LoginService.setLoginStatus(false);
        LoginService.setUser(null);

    });
    $scope.user = LoginService.getUser();
    $scope.countryList = {};

    $http.get("http://mylostcar.com/StolenVehicle/countries").then(function(response) {
        $scope.countryList = response.data;
    }, function(data) {

    });

    $scope.loginStatus = function() {
        return LoginService.loginStatus();
    };

    $scope.placeMarker = function(e) {
        var ll = e.latLng;
        $scope.positions = [];
        $scope.positions.push({
            lat: ll.lat(),
            lng: ll.lng()
        });
        $scope.user.addressCordinates = "[" + e.latLng.lat() + "," + e.latLng.lng() + "]";
    };

    $scope.updateProfile = function(user) {

        var modalRequest = {};
        modalRequest.method = 'post';
        modalRequest.url = 'http://mylostcar.com/StolenVehicle/user';
        modalRequest.entityAttribute = 'user';
        modalRequest.payLoad = $scope.user;
        modalRequest.successMessage = 'Profile details updated';
        modalRequest.modalTime = 2000;

        modalRequest.modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'dialog/loader.html',
            controller: 'ModalController',
            size: 'md',
            resolve: {
                request: modalRequest
            }

        });

        //Check the response
        modalRequest.modalInstance.result.then(function(result) {
            $window.location = '/#/landing';
        }, function() {

        });


    };

    $scope.updatePassword = function(user) {

        var modalRequest = {};
        modalRequest.method = 'post';
        modalRequest.url = 'http://mylostcar.com/StolenVehicle/password';
        modalRequest.entityAttribute = 'password';
        modalRequest.payLoad = {};
        modalRequest.payLoad.userId = $scope.user.id;
        modalRequest.payLoad.newPassword = $scope.user.newPpassword;
        modalRequest.payLoad.oldPassword = $scope.user.password;

        modalRequest.successMessage = 'Password updated successfully';
        modalRequest.modalTime = 2000;

        modalRequest.modalInstance = $uibModal.open({
            animation: true,
            templateUrl: 'dialog/loader.html',
            controller: 'ModalController',
            size: 'md',
            resolve: {
                request: modalRequest
            }

        });

        //Check the response
        modalRequest.modalInstance.result.then(function(result) {
            $window.location = '/#/landing';
        }, function() {

        });
    }

});
