app.controller('ModalController', function($rootScope, $scope, $uibModal, $log, $http, request, LoginService, $timeout) {

    $scope.animationsEnabled = true;
    $scope.request = request;
    $scope.failure = true;
    $scope.errorMessageLabel = "";
    $scope.showLoader = true;
    $scope.data = {};

    $scope.ok = function() {
        request.modalInstance.dismiss('cancel');
    };

    $scope.cancel = function() {
        request.modalInstance.dismiss('cancel');
    };

    $scope.operation = function() {
        //we build the request object here

        if (request.method == "post") {
            var requestObject = {};
            requestObject[request.entityAttribute] = request.payLoad;
            $http.post(request.url, requestObject).then(function(response) {
                    //$scope.data = response.data;
                    $scope.showLoader = false;
                    //when its success we want callie to give us the message
                    $scope.errorMessageLabel = request.successMessage;
                    $scope.failure = false;
                    if (request.modalTime === "undefined") {
                        //not sure what do to here
                    } else {
                        $timeout(function() {
                            request.modalInstance.close(response.data);
                        }, request.modalTime);
                    }
                }, function(response) {

                    //case of failure
                    $scope.showLoader = false;
                    //this is what server should give in case of error
                    if (typeof response.data.error === "undefined") {

                        $scope.errorMessageLabel = "Oops something went wrong !!!. Kindly contact customer service...";

                    } else {

                        $scope.errorMessageLabel = response.data.error.message;
                    }
                    if (request.modalTime === "undefined") {
                        //not sure what do to here
                    } else {
                        $timeout(function() {
                            request.modalInstance.close(response.data);
                        }, request.modalTime);
                    }

                }

            );
        } else if (request.method == "get") {

            $http.get(request.url).then(function(response) {
                    $scope.showLoader = false;
                    $scope.failure = false;
                    if (request.modalTime === "undefined") {
                        //not sure what do to here
                    } else {
                        $timeout(function() {
                            request.modalInstance.close(response.data);
                        }, request.modalTime);
                    }

                }, function(response) {
                    $scope.showLoader = false;
                    if (typeof response.data.error === "undefined") {

                        $scope.errorMessageLabel = "Oops something went wrong !!!. Kindly contact customer service...";

                    } else {

                        $scope.errorMessageLabel = response.data.error.message;
                    }
                    if (request.modalTime === "undefined") {
                        //not sure what do to here
                    } else {
                        $timeout(function() {
                            request.modalInstance.close(response.data);
                        }, request.modalTime);
                    }
                }

            );

        } else if ($scope.request.method == "show") {
            $scope.showLoader = false;
            $scope.errorMessageLabel = request.message;

        }

    };
    $scope.operation();
});
