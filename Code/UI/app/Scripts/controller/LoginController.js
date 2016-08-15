app.controller('LoginController', function($scope, $http, $uibModal,LoginService,$window) {

    //all labels section go here
    $scope.lc_mainMessage = "Please login to repot your lost vehicle";
    $scope.lc_emailAddressLabel = "Email Address";
    $scope.lc_passowrd = "Password";
    $scope.lc_submit = "Login";
    $scope.lc_register = "Sign Up";
    $scope.lc_forgotPassword = "Forgot Password";

    $scope.user = {};
    $scope.user.emailaddress = "";
    $scope.user.password = "";
    $scope.modalInstance = null;

    $scope.appLogin = function(user) {

        var modalRequest = {};
        modalRequest.method = 'post';
        modalRequest.url = 'http://localhost/StolenVehicle/login';
        modalRequest.entityAttribute = 'user';
        modalRequest.payLoad = $scope.user;
        modalRequest.successMessage = 'Welcome';
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
            LoginService.setLoginStatus(true);
            $window.location='/app/#/landing';
        }, function() {
          LoginService.setLoginStatus(false);
          LoginService.setUser(null);
        });
    };

});
