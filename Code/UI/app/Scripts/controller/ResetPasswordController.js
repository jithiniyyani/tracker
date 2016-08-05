app.controller('ResetPasswordController', function($scope, $http, $uibModal) {

     $scope.resetPassword = {};
     $scope.request={};

     $scope.resetPasswordForUser = function(user) {

        $scope.request.method = 'post';
        $scope.request.url = 'http://localhost/StolenVehicle/restPassword';
        $scope.request.payLoad = $scope.resetPassword;
        $scope.request.entityAttribute = 'resetPassword';
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

});
