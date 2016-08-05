app.controller('LandingPageController', function($scope,LoginService, $http) {


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
});
