var com;
(function (com) {
    var config;
    (function (config) {
        var ConfigSetting = (function () {
            function ConfigSetting() {
                this.ajaxTimeout = "3000";
            }
            return ConfigSetting;
        })();
        config.ConfigSetting = ConfigSetting;
    })(config = com.config || (com.config = {}));
})(com || (com = {}));
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
/// <reference path="Config/serverSettings.ts" />
/// <reference path="Controllers/CarController.ts" />
/// <reference path="Controllers/MainController.ts" />
//grunt-end 
//# sourceMappingURL=out.js.map