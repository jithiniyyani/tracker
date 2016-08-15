app.controller('RewardsController',function($scope,$http,$uibModal){

    $scope.message = "Message";
    $scope.request = {};

    $scope.findInfoListReadyForReward;

    $http.get("http://localhost/StolenVehicle/findInformationListReadyForReward").then(function(response) {
        $scope.findInfoListReadyForReward = response.data.find_info_list;
    }, function(data) {

    });

    $scope.giveReward  = function(findInfo){

      var reward = {};
      reward.theftId = findInfo.theft_information_id;
      reward.findInformationId = findInfo.id;
      $scope.request.method = 'post';
      $scope.request.url = 'http://localhost/StolenVehicle/reward';
      $scope.request.payLoad = reward;
      $scope.request.entityAttribute = 'reward';
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

    }

    $scope.rejectReward  = function(findInfo){


    }
});
