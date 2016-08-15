app.controller('UploadController', ['$scope', 'Upload', '$timeout', function($scope, Upload, $timeout) {

    $scope.operation = 'profile';

    $scope.setOperation =  function(operation){
      $scope.operation = operation;
    }

    $scope.uploadFiles = function(file, errFiles) {

        var postUrl = null;
        if($scope.operation  == "profile"){
            postUrl = 'http://localhost/StolenVehicle/uploadAttachmentsForTheft';
        }else if($scope.operation  == "find"){
            postUrl = 'http://localhost/StolenVehicle/uploadAttachmentsForFind';
        }
        $scope.f = file;
        $scope.errFile = errFiles && errFiles[0];
        if (file) {
            file.upload = Upload.upload({
                url: postUrl,
                data: {
                    file: file
                }
            });

            file.upload.then(function(response) {
                $timeout(function() {
                    file.result = response.data;
                });
            }, function(response) {
                if (response.status > 0)
                    $scope.errorMsg = response.status + ': ' + response.data;
            }, function(evt) {
                file.progress = Math.min(100, parseInt(100.0 *
                    evt.loaded / evt.total));
            });
        }
    }
}]);
