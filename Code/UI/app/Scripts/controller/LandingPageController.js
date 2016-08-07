app.controller('LandingPageController', function($scope,LoginService, $http,$window,$uibModal) {

    $scope.request = {};
    $scope.init = function() {

        $http.get("http://localhost/StolenVehicle/user").then(function(data) {
            LoginService.setLoginStatus(true);
            LoginService.setUser(data.data.user);
        }, function(data) {
            console.log(data);
            LoginService.setLoginStatus(false);
            LoginService.setUser(null);

        });
    };
    $scope.init();


    $scope.registerTheft = function(){

        if(LoginService.loginStatus()){

                $window.location='/app/#/lost';

        }else{

                //show a dialog which states kindly login/register before registering a theft
              $scope.request.method = 'show';
              $scope.request.message = 'Kindly login/register with us before registering your theft';
              $scope.request.modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'dialog/loader.html',
                    controller: 'ModalController',
                    size: 'md',
                    resolve: {
                        request: $scope.request
                    }

                });
                $window.location='/app/#/login';
        }

    }
});
