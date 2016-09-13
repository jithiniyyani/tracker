app.controller('VehicleViewController',function($scope,$window,request){


    $scope.request = request;
    $scope.theft_info_view = request.theftInfo;
    $scope.modalInstance = request.modalInstance;

    $scope.confirm = function(){

        $scope.request.modalInstance.dismiss('cancel');
        $window.location='/#/found/upload';

    }


    $scope.cancel = function(){

        $scope.request.modalInstance.dismiss('cancel');

    }
});
