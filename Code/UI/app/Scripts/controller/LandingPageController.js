app.controller('LandingPageController', function($scope,LoginService, $http,$window,$uibModal) {

    $scope.request = {};

    //try to get the user details.
    //After login we are redirecting the user to this page

    $http.get("http://mylostcar.com/StolenVehicle/user").then(function(response) {
            LoginService.setLoginStatus(true);
            LoginService.setUser(response.data.user);
        }, function(data) {
            LoginService.setLoginStatus(false);
            LoginService.setUser(null);

    });



    $scope.registerTheft = function(){

        if(LoginService.loginStatus()){

                $window.location='/#/lost';

        }else{

                //show a dialog which states kindly login/register before registering a theft
              var modalRequest = {};
              modalRequest.method = 'show';
              modalRequest.message = 'Kindly login/register with us before registering your theft';
              modalRequest.modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'dialog/loader.html',
                    controller: 'ModalController',
                    size: 'md',
                    resolve: {
                        request: modalRequest
                    }

                });
                $window.location='/#/login';
        }

    }
});
