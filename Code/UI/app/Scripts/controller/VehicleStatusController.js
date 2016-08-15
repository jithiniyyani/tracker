app.controller('VehicleStatusController', function($scope, LoginService, $http,$uibModal) {

    $scope.theft_info = null;

    $scope.getTheftStatusByVehicleRegNumber = function(registrationNumber) {

        var modalRequest = {};
        modalRequest.method = 'get';
        modalRequest.url = 'http://localhost/StolenVehicle/searchForTheft?regNumber=' + registrationNumber;
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

            $scope.theft_info = result.theft_info;

        }, function() {

        });

    };
});
