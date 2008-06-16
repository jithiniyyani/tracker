var com;
(function (com) {
    var controllers;
    (function (controllers) {
        var carController = (function () {
            function carController($scope) {
                this.name = "Default car";
                $scope.vm = this;
            }
            carController.prototype.getName = function () {
                return this.name;
            };
            return carController;
        })();
        controllers.carController = carController;
    })(controllers = com.controllers || (com.controllers = {}));
})(com || (com = {}));
var com;
(function (com) {
    var controllers;
    (function (controllers) {
        var mainController = (function () {
            function mainController($scope) {
                this.name = "This comes from the first controller";
                $scope.vm = this;
            }
            mainController.prototype.displayName = function () {
                return this.name;
            };
            return mainController;
        })();
        controllers.mainController = mainController;
    })(controllers = com.controllers || (com.controllers = {}));
})(com || (com = {}));
//grunt-start
/// <reference path="Controllers/CarController.ts" />
/// <reference path="Controllers/MainController.ts" />
//grunt-end 
//# sourceMappingURL=out.js.map