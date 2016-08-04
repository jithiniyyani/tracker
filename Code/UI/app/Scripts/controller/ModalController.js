app.controller('ModalController', function ($rootScope,$scope, $uibModal, $log) {

  $scope.animationsEnabled = true;

  $scope.open = function (size,file,linked_controller,paramters) {

	  //there should be some better than this
	  $rootScope.data = paramters;
	  //don not review
	  var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
      templateUrl: file,
      controller: linked_controller,
      size: size,
      resolve: {
          items: function () {
              return $scope.items;
            }
          }
    });

  };


  $scope.test = function(){

        var modalInstance = $uibModal.open({
        animation: true,
        templateUrl: 'dialog/loader.html',
        controller: 'LoginController',
        size: 'md',
        resolve: {
            items: function () {
                return $scope.items;
              }
            }
      });

  }

});


/*
app.controller('ModalDemoCtrl', function ($scope, $uibModal, $log) {

  $scope.items = ['item1', 'item2', 'item3'];

  $scope.animationsEnabled = true;

  $scope.open = function (size,file) {

	  var modalInstance = $uibModal.open({
      animation: $scope.animationsEnabled,
      templateUrl: file,
      controller: 'ModalInstanceCtrl',
      size: size,
      resolve: {
        items: function () {
          return $scope.items;
        }
      }
    });

    modalInstance.result.then(function (selectedItem) {
      $scope.selected = selectedItem;
    }, function () {
      $log.info('Modal dismissed at: ' + new Date());
    });
  };

  $scope.toggleAnimation = function () {
    $scope.animationsEnabled = !$scope.animationsEnabled;
  };

});

// Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

app.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.ok = function () {
    $uibModalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});*/
