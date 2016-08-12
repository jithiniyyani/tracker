app.controller('VehicleViewController',function($scope,request){

    $scope.theft_info_view = request.theft_info;

    $scope.confirm = function(){

        alert("I confirm the find");
        
    }


    $scope.cancel = function(){

            alert("I cancel the find");

    }
});
