app.controller('ModalController', function ($rootScope,$scope, $uibModal, $log,$http,request,LoginService,$timeout) {

  $scope.animationsEnabled = true;
  $scope.request = request;
  $scope.failure = true;
  $scope.errorMessageLabel = "";
  $scope.showLoader = true;
  $scope.data = {};
  $scope.ok = function (){
      $scope.request.modalInstance.dismiss('cancel');
  };
  $scope.cancel = function(){
      $scope.request.modalInstance.dismiss('cancel');
  };
  $scope.operation = function() {

    var requestObject = {};
    requestObject[$scope.request.entityAttribute] = $scope.request.payLoad;
    if($scope.request.method == "post"){

      $http.post($scope.request.url, requestObject).then(function(data) {
        $scope.data = data.data;
        $scope.showLoader = false;
        $scope.errorMessageLabel = data.data.message;
        $timeout(function() {
            $scope.request.modalInstance.close($scope.data);
        }, 500);
        $scope.failure = false;
      }, function(data) {
        $scope.showLoader = false;
        $scope.errorMessageLabel = data.data.error.message;
      }

      );
    }else if($scope.request.method == "get"){

      $http.get($scope.request.url).then(function(data) {
        $scope.data = data.data;
        $scope.showLoader = false;
        $timeout(function() {
            $scope.request.modalInstance.close($scope.data);
        }, 500);
        $scope.errorMessageLabel = "Success";
        $scope.failure = false;
        }, function(data) {
        $scope.showLoader = false;
        $scope.errorMessageLabel = data.data.error.message;
        }

      );

    }
	};
  $scope.operation();
});
