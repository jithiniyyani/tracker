app.controller('LoginController', function($scope, $http, $uibModal,LoginService) {

    //all labels section go here
    $scope.lc_mainMessage = "Please login to repot your lost vehicle";
    $scope.lc_emailAddressLabel = "Email Address";
    $scope.lc_passowrd = "Password";
    $scope.lc_submit = "Login";
    $scope.lc_register = "Register";
    $scope.lc_forgotPassword = "Forgot Password";
    $scope.request = {};
    $scope.user = {};
    $scope.user.emailaddress = "";
    $scope.user.password = "";
    $scope.modalInstance = null;

    $scope.appLogin = function(user) {

        $scope.request.method = 'post';
        $scope.request.url = 'http://localhost/StolenVehicle/login';
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
            LoginService.setLoginStatus(true);
            //LoginService.setUser(result);
        }, function() {
          LoginService.setLoginStatus(false);
          LoginService.setUser(null);
        });
    };

});
